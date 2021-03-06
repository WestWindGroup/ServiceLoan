package com.serviceloan.validator;

import com.serviceloan.model.Payment;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

/**
 * Validator for {@link Payment} class.
 *
 * @author Eugene Artemenko
 * @version 1.0
 */

@Component
public class PaymentValidator extends NumberValidator{


    public boolean validate(BigDecimal amountPay,BigDecimal minPayment,BigDecimal maxPayment) {
        if((amountPay.compareTo(minPayment) == -1)|| (amountPay.compareTo(maxPayment) == 1)) {
            return false;
        }else{
            return true;
        }
    }


    public boolean checkCorrectAmount(BigDecimal amount,BigDecimal minPayment,BigDecimal maxPayment){

        if (validate(amount,minPayment,maxPayment)){
            return true;
        } else{
            return false;
        }
    }
}
