package four.lab.controller;

import java.util.Date;
import java.text.ParseException;

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
import four.lab.exception.UnsupportedCodeException;
import four.lab.exception.ValidationFailedException;
import four.lab.model.Codes;
import four.lab.model.ErrorCodes;
import four.lab.model.ErrorMessages;
import four.lab.model.Request;
import four.lab.model.Response;
import four.lab.service.ModifyResponseService;
import four.lab.service.ValidationService;
import four.lab.util.DateTimeUtil;

@Slf4j
@RestController
public class MyController {

    private final ValidationService validationService;
    private final ModifyResponseService modifyResponseService;


    @Autowired
    public MyController(ValidationService validationService,
    @Qualifier("ModifySystemTimeResponseService") ModifyResponseService modifyResponseService){
        this.validationService = validationService;
        this.modifyResponseService = modifyResponseService;
    }

    @PostMapping(value="/feedback")
    public ResponseEntity<Response> feedback(
        @Valid @RequestBody Request request,
        BindingResult bindingResult
        ){
            log.info("request: {}", request);
            
            String time_now = DateTimeUtil.getCustomFormat().format(new Date());
            
            try {
                Date startTime = DateTimeUtil.getCustomFormat().parse(request.getSystemTime());
                Date nowTime = DateTimeUtil.getCustomFormat().parse(time_now);
                long timeDifferenceMs = nowTime.getTime() - startTime.getTime();
                log.info("Отправка запроса заняла: {} миллисекунд", timeDifferenceMs);
            } catch (ParseException e) {
                log.error("Ошибка при парсинге времени: {}", e.getMessage());
            }
            
            
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
            log.info("После модификации response: {}", response);

            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    

}
