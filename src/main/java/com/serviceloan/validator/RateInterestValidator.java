package com.serviceloan.validator;

import com.serviceloan.model.RateInterest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validator for {@link RateInterest} class.
 *
 * @author Eugene Artemenko
 * @version 1.0
 */

@Component
public class RateInterestValidator extends NumberValidator {


    public boolean validate(String str){
        return super.validate(str);
    }


    public boolean validate(double rate) {
        if((rate < Double.parseDouble(env.getProperty("key.min.rate")))||
                (rate > Double.parseDouble(env.getProperty("key.max.rate")))) {
            return false;
        }else{
            return true;
        }
    }

}
