package com.serviceloan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller for basic pages
 *
 * @author Eugene Artemenko
 */

@Controller
public class BaseController {

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcome() {
        return "welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout,
                        HttpServletRequest request, CookieLocaleResolver localeResolver) {
        String msg =  messageSource.
                getMessage("key.usernameOrPassword.incorrect", null,
                        localeResolver.resolveLocale(request));

        if (error != null) {
            model.addAttribute("error", msg);
        }

        if (logout != null) {
            model.addAttribute("message", "Logged out successfully.");
        }

        return "login";
    }

}
