package com.serviceloan.validator;


import com.serviceloan.model.CreditDuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import javax.servlet.http.HttpServletRequest;

/**
 * Validator for {@link CreditDuration} class.
 *
 * @author Eugene Artemenko
 * @version 1.0
 */

@Component
public class CreditDurationValidator  extends NumberValidator{

    public boolean validate(String str){
        if(!checkIntWithRegExp(str)){
            return false;
        }else{
            return true;
        }
    }


    private boolean validate(int duration) {
        if((duration < Double.parseDouble(env.getProperty("key.min.rate")))||
                (duration > Double.parseDouble(env.getProperty("key.max.rate")))) {
            return false;
        }else{
            return true;
        }
    }


    public boolean checkCorrectDuration(String newDuration, CreditDuration duration, ModelAndView modelAndView,
                                    HttpServletRequest request, CookieLocaleResolver localeResolver) {

        String min_duration = String.valueOf(env.getProperty("key.min.duration"));
        String max_duration = String.valueOf(env.getProperty("key.max.duration"));
        String msg = messageSource.getMessage(
                "key.credit", new String[]{min_duration, max_duration}, localeResolver.resolveLocale(request));
        if (!newDuration.equals("")) {
            if (validate(newDuration)) {
                duration.setDuration(Integer.parseInt(newDuration));
            } else {
                modelAndView.addObject("errorDuration",
                        messageSource.getMessage("incorrectValue", null, localeResolver.resolveLocale(request)));
                modelAndView.setViewName("admin/creditDuration/addDuration");
                modelAndView.addObject("duration", duration);
                return false;
            }
        } else {
            modelAndView.addObject("errorDuration",msg);
            modelAndView.setViewName("admin/creditDuration/addDuration");
            modelAndView.addObject("duration", duration);
            return false;
        }

        if (validate(duration.getDuration())) {
            return true;
        } else {
            modelAndView.addObject("errorDuration",msg);
            modelAndView.setViewName("admin/creditDuration/addDuration");
            modelAndView.addObject("duration", duration);
            return false;
        }
    }
}
