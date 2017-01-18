package com.serviceloan.validator;

import com.serviceloan.model.CreditStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

/**
 * Validator for {@link CreditStatus} class,
 * add functionality in {@link AbstractValidator} class.
 *
 * @author Eugene Artemenko
 * @version 1.0
 */

@Component
public class CreditStatusValidator extends AbstractValidator{
    @Override
    public boolean supports(Class<?> aClass) {
        return CreditStatus.class.equals(aClass);
    }

    @Override
    public void validate(Object ob, Errors errors) {
        validateField("status",errors);
    }
}
