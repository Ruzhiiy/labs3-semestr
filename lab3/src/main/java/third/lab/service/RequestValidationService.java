package third.lab.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import lombok.extern.slf4j.Slf4j;
import third.lab.exception.UnsupportedCodeException;
import third.lab.exception.ValidationFailedException;

@Slf4j
@Service
public class RequestValidationService implements ValidationService {

    @SuppressWarnings("null")
    public void isValid(BindingResult bindingResult) throws ValidationFailedException{
        if(bindingResult.hasErrors()){
            log.error("Запрос не валиден!");
            log.error("Ошибки валидации: errors: {}",
                        bindingResult.getAllErrors()
                                     .stream()
                                     .map(error -> error.getDefaultMessage())
                                     .collect(java.util.stream.Collectors.joining("; ")));
            throw new ValidationFailedException(bindingResult.getFieldError().toString());
        }
    }

    public void isUnsupported(String uid) throws UnsupportedCodeException{
        if(uid.equals("123")){
            log.error("Не поддерживаемое значение Uid: {}", uid);
            throw new UnsupportedCodeException("Не допустимое значение uid!: " + uid);
    }

    }
}
