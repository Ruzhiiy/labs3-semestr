package third.lab.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import third.lab.exception.UnsupportedCodeException;
import third.lab.exception.ValidationFailedException;

@Service
public interface ValidationService {
    void isValid(BindingResult bindingResult) throws ValidationFailedException;

    void isUnsupported(String uid) throws UnsupportedCodeException;
}
