package four.lab.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import four.lab.exception.UnsupportedCodeException;
import four.lab.exception.ValidationFailedException;

@Service
public interface ValidationService {
    void isValid(BindingResult bindingResult) throws ValidationFailedException;

    void isUnsupported(String uid) throws UnsupportedCodeException;
}
