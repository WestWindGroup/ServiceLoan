package com.serviceloan.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Configuration
@PropertySource("classpath:validation/validationSettings.properties")
public abstract class AbstractValidator implements Validator {


    @Autowired
    protected Environment env;


    protected void validateField(String field, Errors errors) {

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, field, "key.required");
    }

    protected boolean checkLastAndFirstNameWithRegExp(String str){
        Pattern p = Pattern.compile("^[A-Za-zА-Яа-яЁёІіЇїЄє]$");
        Matcher m = p.matcher(str);
        return m.matches();
    }

    protected boolean checkUserNameWithRegExp(String str,int... key){
        Pattern p = Pattern.compile("^[a-zA-Z][a-zA-Z0-9-_.]{" + key[0] + "," + key[1] + "}$");
        Matcher m = p.matcher(str);
        return m.matches();
    }

    protected boolean checkPasswordWithRegExp(String str,int... key){
        Pattern p = Pattern.compile(
                "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[^\\w\\s]).{"
                        + key[0] + "," + key[1] + "}$");
        Matcher m = p.matcher(str);
        return m.matches();
    }

}
