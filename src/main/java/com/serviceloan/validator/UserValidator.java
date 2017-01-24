package com.serviceloan.validator;

import com.serviceloan.model.User;
import com.serviceloan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;

/**
 * Validator for {@link User} class,
 * add functionality in {@link AbstractValidator} class.
 *
 * @author Eugene Artemenko
 * @version 1.0
 */

public abstract class UserValidator  extends AbstractValidator {


    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }


    protected void validateUsernameCoincidence(User user, Errors errors){

        if (userService.findByUserName(user.getUsername()) != null) {
            errors.rejectValue("username", "key.duplicate.userForm.username");
        }
    }

    protected void validateUsername(User user, Errors errors){

        validateField("username",errors);

        if(!checkUserNameWithRegExp(user.getUsername(),
                Integer.parseInt(env.getProperty("key.min.count.characters.username")),
                Integer.parseInt(env.getProperty("key.max.count.characters.username")))){
            user.setUsername("");
            errors.rejectValue("username", "key.size.userForm.username",
                    new String[]{String.valueOf(env.getProperty("key.min.count.characters.username")),
                            String.valueOf(env.getProperty("key.max.count.characters.username"))}, null);
        }
    }

    protected void validateFirstName(User user, Errors errors){

        validateField("firstName",errors);

        if(!checkLastAndFirstNameWithRegExp(user.getFirstName())){
            user.setFirstName("");
            errors.rejectValue("firstName", "key.client.firstname", null, null);
        }
    }

    protected void validateLastName(User user, Errors errors){

        validateField("lastName",errors);

        if(!checkLastAndFirstNameWithRegExp(user.getLastName())){
            user.setLastName("");
            errors.rejectValue("lastName", "key.client.lastname", null, null);
        }
    }


    protected void validatePassword(User user, Errors errors) {

        validateField("password",errors);

        if (!checkPasswordWithRegExp(user.getPassword(),
                Integer.parseInt(env.getProperty("key.min.count.characters.password")),
                Integer.parseInt(env.getProperty("key.max.count.characters.password")))) {
            user.setPassword("");
            errors.rejectValue
                    ("password", "key.size.userForm.password",
                            new String[]{String.valueOf(env.getProperty("key.min.count.characters.password")),
                                    String.valueOf(env.getProperty("key.max.count.characters.password"))}, null);
        }

        if (!user.getConfirmPassword().equals(user.getPassword())) {
            user.setConfirmPassword("");
            errors.rejectValue("addUser.confirmPassword", "key.different.userForm.password");
        }

    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

}
