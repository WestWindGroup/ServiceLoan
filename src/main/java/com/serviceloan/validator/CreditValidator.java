package com.serviceloan.validator;

import com.serviceloan.model.Credit;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Validator for {@link Credit} class.
 *
 * @author Eugene Artemenko
 * @version 1.0
 */

@Component
public class CreditValidator extends NumberValidator{

    public boolean validate(String str){
        return super.validate(str);
    }

    public boolean validate(BigDecimal amount) {
        if((amount.compareTo(new BigDecimal(env.getProperty("key.min.amount"))) < 1)||
                (amount.compareTo(new BigDecimal(env.getProperty("key.max.amount"))) > 1)) {
            return false;
        }else{
            return true;
        }
    }

}
