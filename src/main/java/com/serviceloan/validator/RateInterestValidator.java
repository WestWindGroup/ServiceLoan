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


    public boolean validate(String str){
        if(!checkDoubleWithRegExp(str)){
            return false;
        }else{
            return true;
        }
    }


    private boolean validate(double rate) {
        if((rate < Double.parseDouble(env.getProperty("key.min.rate")))||
                (rate > Double.parseDouble(env.getProperty("key.max.rate")))) {
            return false;
        }else{
            return true;
        }
    }


    public boolean checkCorrectRate(String newRate, RateInterest rate, ModelAndView modelAndView,
                                     HttpServletRequest request, CookieLocaleResolver localeResolver) {

        String min_rate = String.valueOf(env.getProperty("key.min.rate"));
        String max_rate = String.valueOf(env.getProperty("key.max.rate"));
        String msg = messageSource.getMessage(
                "key.credit", new String[]{min_rate, max_rate}, localeResolver.resolveLocale(request));
        if (!newRate.equals("")) {
            if (validate(newRate)) {
                rate.setRate(Double.parseDouble(newRate));
            } else {
                modelAndView.addObject("errorRate",
                        messageSource.getMessage("incorrectValue", null, localeResolver.resolveLocale(request)));
                modelAndView.setViewName("admin/rateInterest/addRate");
                modelAndView.addObject("rate", rate);
                return false;
            }
        } else {
            modelAndView.addObject("errorRate",msg);
            modelAndView.setViewName("admin/rateInterest/addRate");
            modelAndView.addObject("rate", rate);
            return false;
        }

        if (validate(rate.getRate())) {
            return true;
        } else {
            modelAndView.addObject("errorRate",msg);
            modelAndView.setViewName("admin/rateInterest/addRate");
            modelAndView.addObject("rate", rate);
            return false;
        }
    }

}
