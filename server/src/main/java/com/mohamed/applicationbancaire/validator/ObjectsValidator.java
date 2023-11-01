package com.mohamed.applicationbancaire.validator;

import com.mohamed.applicationbancaire.exeptions.ObjectValidationException;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ObjectsValidator<T> {

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    public void validate(T object){

        Set<ConstraintViolation<T>> violations = validator.validate(object);

        if(!violations.isEmpty()){
            Set<String> errorMessages = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toSet());

            //TODO vous pouvez maintenant générer une excéption
            throw new ObjectValidationException(errorMessages, object.getClass().getName());
        }
    }

}
