package com.serviceloan.validator;


import com.serviceloan.model.CreditType;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

/**
 * Validator for {@link CreditType} class,
 * add functionality in {@link AbstractValidator} class.
 *
 * @author Eugene Artemenko
 * @version 1.0
 */

@Component
public class CreditTypeValidator extends AbstractValidator {

    @Override
    public boolean supports(Class<?> aClass) {
        return CreditType.class.equals(aClass);
    }

    @Override
    public void validate(Object ob, Errors errors) {
        validateField("type",errors);
    }
}
