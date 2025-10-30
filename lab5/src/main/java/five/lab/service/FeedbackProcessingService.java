package five.lab.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import five.lab.exception.UnsupportedCodeException;
import five.lab.exception.ValidationFailedException;
import five.lab.model.Codes;
import five.lab.model.ErrorCodes;
import five.lab.model.ErrorMessages;
import five.lab.model.Request;
import five.lab.model.Response;
import five.lab.util.DateTimeUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FeedbackProcessingService {

    private final ValidationService validationService;
    private final ModifyResponseService modifyResponseService;
    private final ModifyRequestService modifyRequestService;
    private final ErrorHandlingService errorHandlingService;
    private final AnnualBonusService annualBonusService;

    public FeedbackProcessingService(
            ValidationService validationService,
            @Qualifier("ModifySystemTimeResponseService") ModifyResponseService modifyResponseService,
            @Qualifier("ModifySourceRequestService") ModifyRequestService modifyRequestService,
            ErrorHandlingService errorHandlingService,
            AnnualBonusService annualBonusService) {
        this.validationService = validationService;
        this.modifyResponseService = modifyResponseService;
        this.modifyRequestService = modifyRequestService;
        this.errorHandlingService = errorHandlingService;
        this.annualBonusService = annualBonusService;
    }

    public ResponseEntity<Response> processFeedback(Request request, BindingResult bindingResult) {
        log.info("request: {}", request);

        Response response = buildResponse(request);
        log.info("response: {}", response);

        try {
            validate(request, bindingResult);
        } catch (ValidationFailedException e) {
            return errorHandlingService.handleValidationError(response);
        } catch (UnsupportedCodeException e) {
            return errorHandlingService.handleUnsupportedUid(response);
        } catch (Exception e) {
            return errorHandlingService.handleUnknownException(response);
        }

        modifyResponseService.modify(response);
        modifyRequestService.modify(request);

        double annualBonus = annualBonusService.calculate(
            request.getPosition(),
            request.getSalary(),
            request.getBonus(),
            request.getWorkDays());
    
        double quarterlyBonus = annualBonusService.calculateQuarterlyBonus(
            request.getPosition(),
            request.getSalary(),
            request.getBonus(),
            request.getWorkDays());

        response.setAnnualBonus(annualBonus);
        response.setQuartarlyBonus(quarterlyBonus);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void validate(Request request, BindingResult bindingResult)
            throws UnsupportedCodeException, ValidationFailedException {
        log.info("Проверка Uid");
        validationService.isUnsupported(request.getUid());
        log.info("Валидация запроса");
        validationService.isValid(bindingResult);
    }

    private Response buildResponse(Request request) {
        Response response = Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(DateTimeUtil.getCustomFormat().format(new Date()))
                .code(Codes.SUCCESS)
                .errorCode(ErrorCodes.EMPTY)
                .errorMessage(ErrorMessages.EMPTY)
                .build();
        return response;}
}
