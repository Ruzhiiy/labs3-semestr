package third.lab.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import third.lab.exception.UnsupportedCodeException;
import third.lab.exception.ValidationFailedException;
import third.lab.model.Codes;
import third.lab.model.ErrorCodes;
import third.lab.model.ErrorMessages;
import third.lab.model.Request;
import third.lab.model.Response;
import third.lab.service.ModifyRequestService;
import third.lab.service.ModifyResponseService;
import third.lab.service.ValidationService;
import third.lab.util.DateTimeUtil;

@Slf4j
@RestController
public class MyController {

    private final ValidationService validationService;
    private final ModifyResponseService modifyResponseService;
    private final ModifyRequestService modifyRequestService;


    @Autowired
    public MyController(ValidationService validationService,
    @Qualifier("ModifySystemTimeResponseService") ModifyResponseService modifyResponseService,
    @Qualifier("ModifySourceRequestService") ModifyRequestService modifyRequestService){
        this.validationService = validationService;
        this.modifyResponseService = modifyResponseService;
        this.modifyRequestService = modifyRequestService;
    }

    @PostMapping(value="/feedback")
    public ResponseEntity<Response> feedback(
        @Valid @RequestBody Request request,
        BindingResult bindingResult
        ){
            log.info("request: {}", request);
            log.info("Создается объект Response");

            Response response = Response.builder()
            .uid(request.getUid())
            .operationUid(request.getOperationUid())
            .systemTime(DateTimeUtil.getCustomFormat().format(new Date()))
            .code(Codes.SUCCESS)
            .errorCode(ErrorCodes.EMPTY)
            .errorMessage(ErrorMessages.EMPTY)
            .build();

            log.info("response:{}", response);

            try{
                log.info("Проверка Uid");
                validationService.isUnsupported(request.getUid());
                log.info("Валидация запроса");
                validationService.isValid(bindingResult);
            }catch(ValidationFailedException e){
                response.setCode(Codes.FAILED);
                response.setErrorCode(ErrorCodes.VALIDATION_EXCEPTION);
                response.setErrorMessage(ErrorMessages.VALIDATION);
                log.info("response: {}", response);
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            } catch (UnsupportedCodeException e) {
                response.setCode(Codes.FAILED);
                response.setErrorCode(ErrorCodes.UNSUPPORTED_EXCEPTION);
                response.setErrorMessage(ErrorMessages.UNSUPPORTED);
                log.info("response: {}", response);
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            } 
            catch (Exception e){
                response.setCode(Codes.FAILED);
                response.setErrorCode(ErrorCodes.UNKNOWN_EXCEPTION);
                response.setErrorMessage(ErrorMessages.UNKNOWN);
                log.info("response: {}", response);
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        
            log.info("Объект Request валидный, передается в ModifyResponseService");
            log.info("До модификации response: {}", response);
            modifyResponseService.modify(response);
            modifyRequestService.modify(request);
            log.info("После модификации response: {}", response);

            return new ResponseEntity<>(response, HttpStatus.OK);
        }
}
