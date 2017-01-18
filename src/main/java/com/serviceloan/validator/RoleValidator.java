package com.serviceloan.validator;

import com.serviceloan.model.Role;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

/**
 * Validator for {@link Role} class,
 * add functionality in {@link AbstractValidator} class.
 *
 * @author Eugene Artemenko
 * @version 1.0
 */

@Component
public class RoleValidator extends AbstractValidator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Role.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        validateField("name",errors);
    }
}
