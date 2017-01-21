package com.serviceloan.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Component
@Configuration
@PropertySource("classpath:validation/validationSettings.properties")
public abstract class NumberValidator {

    @Autowired
    protected Environment env;

    @Autowired
    protected MessageSource messageSource;

    public String replacedOnComma(String str){
        char[] chArray = str.toCharArray();
        StringBuilder strBuild = new StringBuilder();
        for (int i = 0; i < chArray.length; i++) {
            if ((chArray[i] == ',') || (chArray[i] == '.')){
                chArray[i] = '.';
            }
            strBuild.append(chArray[i]);
        }
        return strBuild.toString();
    }

    public boolean validate(String str){
        if(!checkDoubleWithRegExp(str)){
            return false;
        }else{
            return true;
        }
    }

    public boolean checkCorrect(String num) {
        if (!num.equals("")) {
            if (validate(num)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    protected boolean checkIntWithRegExp(String str){
        Pattern p = Pattern.compile("^([0-9]+)");
        Matcher m = p.matcher(str);
        return m.matches();
    }

    protected boolean checkDoubleWithRegExp(String str){
        Pattern p = Pattern.compile("^([0-9]+\\.[0-9]+)|([0-9]+[,][0-9]+)|([0-9]+)");
        Matcher m = p.matcher(str);
        return m.matches();
    }

    public Environment getEnv() {
        return env;
    }

    public void setEnv(Environment env) {
        this.env = env;
    }
}
