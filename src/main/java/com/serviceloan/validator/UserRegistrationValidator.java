package com.serviceloan.validator;

import com.serviceloan.model.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

/**
 * Validator for {@link User} class, when you register,
 * extend {@link UserValidator} class.
 *
 * @author Eugene Artemenko
 * @version 1.0
 */

@Component
public class UserRegistrationValidator extends UserValidator {


    @Override
    public void validate(Object ob, Errors errors) {
        User user = (User) ob;

        validateUsernameCoincidence(user, errors);

        validateUsername(user,errors);

        validateFirstName(user,errors);

        validateLastName(user,errors);

        validatePassword(user,errors);

    }

}
