package five.lab.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import five.lab.exception.UnsupportedCodeException;
import five.lab.exception.ValidationFailedException;

@Service
public interface ValidationService {
    void isValid(BindingResult bindingResult) throws ValidationFailedException;

    void isUnsupported(String uid) throws UnsupportedCodeException;
}
