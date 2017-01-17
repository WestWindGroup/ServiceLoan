package com.serviceloan.controller;


import com.serviceloan.model.User;
import com.serviceloan.service.SecurityService;
import com.serviceloan.service.UserService;
import com.serviceloan.validator.UserEditValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller for {@link User}'s pages
 *
 * @author Eugene Artemenko
 */

@Controller
@SessionAttributes(value = "user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserEditValidator userValidator;


    @Autowired
    private MessageSource messageSource;


    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String welcome() {
        return "user/operator/home";
    }


    @RequestMapping(value = "/editProfile", method = RequestMethod.GET)
    public ModelAndView authorization(HttpServletRequest request) {
        User user = userService.findByUserName(request.getUserPrincipal().getName());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/editProfile");
        modelAndView.addObject("user", user);

        return modelAndView;
    }

    @RequestMapping(value = "/editProfile", method = RequestMethod.POST)
    public String registration(@ModelAttribute("user") User user, BindingResult bindingResult,
                               Model model, HttpServletRequest request, CookieLocaleResolver localeResolver) {

        User userFromDataBase = userService.findByUserName(request.getUserPrincipal().getName());

        String newPassword = request.getParameter("newPassword");

        if(newPassword.equals("")){
            user.setPassword(userFromDataBase.getPassword());
        }else{
            user.setPassword(newPassword);
        }

        if(!user.equals(userFromDataBase)){

            userValidator.validate(user, bindingResult);

            String oldPassword = request.getParameter("oldPassword");

            userFromDataBase.setConfirmPassword(oldPassword);

            if((oldPassword == null)||(!userValidator.getUserService().
                    coincidencePassword(userFromDataBase.getConfirmPassword(),userFromDataBase.getPassword()))){
                model.addAttribute("errorConfirmPassword",
                        messageSource.getMessage("key.password.incorrect", null, localeResolver.resolveLocale(request)));
                bindingResult.addError(null);
//                return "user/editProfile";
            }

            if (bindingResult.hasErrors()) {
                return "user/editProfile";
            }

            if(user.getPassword().equals(userFromDataBase.getPassword())){
                userService.update(user);
                securityService.autoLogin(user.getUsername(), oldPassword);
            }else{
                userService.save(user);
                securityService.autoLogin(user.getUsername(), user.getConfirmPassword());
            }
        }

        return "user/operator/home";
    }

}