package com.serviceloan.validator;

import com.serviceloan.model.RateInterest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

/**
 * Validator for {@link RateInterest} class.
 *
 * @author Eugene Artemenko
 * @version 1.0
 */

@Component
public class RateInterestValidator extends AbstractValidator {

    public boolean validate(String str) {
        if(!checkDoubleWithRegExp(str)){
            return false;
        }else{
            return true;
        }
    }

    public boolean validate(double rate) {
        if((rate < Double.parseDouble(env.getProperty("key.min.rate")))||
                (rate > Double.parseDouble(env.getProperty("key.max.rate")))) {
            return false;
        }else{
            return true;
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return RateInterest.class.equals(aClass);
    }

    @Override
    public void validate(Object ob, Errors errors) {

    }
}
