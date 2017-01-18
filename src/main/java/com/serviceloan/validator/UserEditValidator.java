package com.serviceloan.validator;

import com.serviceloan.model.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;


/**
 * Validator for {@link User} class, when editing,
 * extend {@link UserValidator} class.
 *
 * @author Eugene Artemenko
 * @version 1.0
 */

@Component
public class UserEditValidator  extends UserValidator {


    @Override
    public void validate(Object ob, Errors errors) {

        User user = (User) ob;
        User userOld = getUserService().getById(user.getId());

        if(!user.getUsername().equals(userOld.getUsername())){
            validateUsernameCoincidence(user, errors);
            validateUsername(user,errors);
        }

        if(!user.getFirstName().equals(userOld.getFirstName())){
            validateFirstName(user,errors);
        }

        if(!user.getLastName().equals(userOld.getLastName())){
            validateLastName(user,errors);
        }

        if(!user.getPassword().equals(userOld.getPassword())){
            validatePassword(user,errors);
        }
    }

}
