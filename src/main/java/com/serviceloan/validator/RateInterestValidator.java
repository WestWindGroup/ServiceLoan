package com.serviceloan.validator;

import com.serviceloan.model.RateInterest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

/**
 * Validator for {@link RateInterest} class, when you register,
 * extend {@link ClientValidator} class.
 *
 * @author Eugene Artemenko
 * @version 1.0
 */

@Component
public class RateInterestValidator extends AbstractValidator {

    @Override
    public boolean supports(Class<?> aClass) {
        return RateInterest.class.equals(aClass);
    }



    @Override
    public void validate(Object ob, Errors errors) {
        RateInterest rateInterest = (RateInterest) ob;

        validateField("rate",errors);

        if(!checkDoubleWithRegExp(String.valueOf(rateInterest.getRate()))){
            errors.rejectValue("rate", "key.rate", new String[]{String.valueOf(env.getProperty("key.min.rare")),
                    String.valueOf(env.getProperty("key.max.rare"))}, null);
        }
    }
}
