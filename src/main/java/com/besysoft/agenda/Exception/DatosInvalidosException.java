package com.besysoft.agenda.Exception;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.stream.Collectors;

public class DatosInvalidosException extends RuntimeException {

    public DatosInvalidosException(BindingResult bindingResult){
        super(bindingResult.getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(". "))
        );
    }
}
