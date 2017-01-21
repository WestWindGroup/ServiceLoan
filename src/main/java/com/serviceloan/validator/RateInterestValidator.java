package com.serviceloan.validator;

import com.serviceloan.model.RateInterest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import javax.servlet.http.HttpServletRequest;
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
