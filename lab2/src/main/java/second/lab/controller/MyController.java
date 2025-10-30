package second.lab.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import second.lab.exception.UnsupportedCodeException;
import second.lab.exception.ValidationFailedException;
import second.lab.model.Request;
import second.lab.model.Response;
import second.lab.service.ValidationService;

@RestController
public class MyController {

    private final ValidationService validationService;

    private static final Logger logger = LoggerFactory.getLogger(MyController.class);

    @Autowired
    public MyController(ValidationService validationService){
        this.validationService = validationService;
    }

    @PostMapping(value="/feedback")
    public ResponseEntity<Response> feedback(
        @Valid @RequestBody Request request,
        BindingResult bindingResult
        ){

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS'Z'");

            Response response = Response.builder()
            .uid(request.getUid())
            .operationUid(request.getOperationUid())
            .systemTime(simpleDateFormat.format(new Date()))
            .code("Success")
            .errorCode("")
            .errorMessage("")
            .build();


            try{
                validationService.isUnsupported(request.getUid());
                validationService.isValid(bindingResult);
            }catch(ValidationFailedException e){

                String messageError = getErrorMessage(bindingResult);
                logger.info("ERORRS: " + messageError);

                response.setCode("failed");
                response.setErrorCode("ValidationException");
                response.setErrorMessage(messageError);
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            } catch (UnsupportedCodeException e) {
                response.setCode("failed");
                response.setErrorCode("UnsupportedCodeException");
                response.setErrorMessage(e.getMessage());
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            } 
            catch (Exception e){
                response.setCode("failed");
                response.setErrorCode("UnknowException");
                response.setErrorMessage("Произошла непрдвиденная ошибка");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            logger.info("Request is OK!!!");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    

    private String getErrorMessage(BindingResult bindingResult){

            List<ObjectError> errors = bindingResult.getAllErrors();
            
            List<String> errorMessages = errors.stream()    
            .map(ObjectError::getDefaultMessage)
            .collect(Collectors.toList());

            return String.join("; ", errorMessages);
    }
}
