package com.serviceloan.validator;

import com.serviceloan.model.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validator for {@link User} class,
 * implements {@link Validator} interface.
 *
 * @author Eugene Artemenko
 * @version 1.0
 */

@Component
public class UserRegistrationValidator extends UserValidator {

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }


    @Override
    public void validate(Object ob, Errors errors) {
        User user = (User) ob;

        validateUsernameCoincidence(user, errors);

        validateUsername(user,errors);

        validateField("firstName",errors);

        validateField("lastName",errors);

        validatePassword(user,errors);

    }

}
