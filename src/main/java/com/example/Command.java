package com.example;

import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.stream.Collectors;

public class Command {

    private final Object objectToValidate;
    private Validator validator;

    public Command(Object objectToValidate) {
        this.objectToValidate = objectToValidate;
        this.validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();
    }

    public boolean isValid() {
        return validator
                .validate(objectToValidate)
                .stream()
                .count() == 0;
    }

    public List<String> getValidationErrors() {
        return validator
                .validate(objectToValidate)
                .stream()
                .map(violation -> violation.getMessage())
                .collect(Collectors.toList());
    }
}