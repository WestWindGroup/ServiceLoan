package com.serviceloan.validator;

import com.serviceloan.model.Credit;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import javax.servlet.http.HttpServletRequest;
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
        if(!checkDoubleWithRegExp(str)){
            return false;
        }else{
            return true;
        }
    }

    private boolean validate(BigDecimal amount) {
        if((amount.compareTo(new BigDecimal(env.getProperty("key.min.amount"))) == -1)||
                (amount.compareTo(new BigDecimal(env.getProperty("key.max.amount"))) == 1)) {
            return false;
        }else{
            return true;
        }
    }

    private String replacedOnComma(String str){
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


    public boolean checkCorrectAmount(String newCredit, Credit credit, ModelAndView modelAndView,
                                       HttpServletRequest request, CookieLocaleResolver localeResolver) {

        String min_amount = String.valueOf(env.getProperty("key.min.amount"));
        String max_amount = String.valueOf(env.getProperty("key.max.amount"));
        String msg = messageSource.getMessage(
                "key.credit", new String[]{min_amount, max_amount}, localeResolver.resolveLocale(request));
        if (!newCredit.equals("")) {
            if (validate(newCredit)) {
                String str = replacedOnComma(newCredit);
                credit.setAmount(new BigDecimal(str));
            } else {
                modelAndView.addObject("errorAmount",
                        messageSource.getMessage("incorrectValue", null, localeResolver.resolveLocale(request)));
                modelAndView.setViewName("user/credit/addCredit");
                modelAndView.addObject("credit", credit);
                return false;
            }
        } else {
            modelAndView.addObject("errorAmount",msg);
            modelAndView.setViewName("user/credit/addCredit");
            modelAndView.addObject("credit", credit);
            return false;
        }

        if (validate(credit.getAmount())) {
            return true;
        } else {
            modelAndView.addObject("errorAmount",msg);
            modelAndView.setViewName("user/credit/addCredit");
            modelAndView.addObject("credit", credit);
            return false;
        }
    }

}
