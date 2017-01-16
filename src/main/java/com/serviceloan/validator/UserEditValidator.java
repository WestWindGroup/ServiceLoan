package com.serviceloan.validator;

import com.serviceloan.model.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class UserEditValidator  extends UserValidator {

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }


    @Override
    public void validate(Object ob, Errors errors) {

        User user = (User) ob;
        User userOld = getUserService().getById(user.getId());

        if(!user.getUsername().equals(userOld.getUsername())){
            validateUsernameCoincidence(user, errors);
            validateUsername(user,errors);
        }

        if(!user.getFirstName().equals(userOld.getFirstName())){
            validateField("firstName",errors);
        }

        if(!user.getLastName().equals(userOld.getLastName())){
            validateField("lastName",errors);
        }

        if(!user.getPassword().equals(userOld.getPassword())){
            validatePassword(user,errors);
        }
    }

}
