package com.serviceloan.validator;

import com.serviceloan.model.RateInterest;
import org.springframework.stereotype.Component;

/**
 * Validator for {@link RateInterest} class.
 *
 * @author Eugene Artemenko
 * @version 1.0
 */

@Component
public class RateInterestValidator extends NumberValidator {




    public boolean validate(double rate,String min,String max) {
        if((rate < Double.parseDouble(min))||
                (rate > Double.parseDouble(max))) {
            return false;
        }else{
            return true;
        }
    }


    public boolean checkCorrectAmount(double rate,String min,String max){

        if (validate(rate,min,max)){
            return true;
        } else{
            return false;
        }
    }

}
