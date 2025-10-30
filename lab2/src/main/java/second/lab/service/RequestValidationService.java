package second.lab.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import second.lab.exception.UnsupportedCodeException;
import second.lab.exception.ValidationFailedException;

@Service
public class RequestValidationService implements ValidationService {

    public void isValid(BindingResult bindingResult) throws ValidationFailedException{
        if(bindingResult.hasErrors()){
            throw new
                ValidationFailedException(bindingResult.getFieldError().toString());
        }
    }

    public void isUnsupported(String uid) throws UnsupportedCodeException{
        if(uid.equals("123")){
            throw new UnsupportedCodeException("Не допустимое значение uid!");
    }

    }
}