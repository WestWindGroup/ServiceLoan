package com.serviceloan.controller;

import com.serviceloan.model.Client;
import com.serviceloan.model.User;
import com.serviceloan.service.ClientService;
import com.serviceloan.service.UserService;
import com.serviceloan.validator.ClientValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller for Admin's pages (list of users, project management, etc.)
 *
 * @author Eugene Artemenko
 */

@Controller
public class ClientController {

    @Autowired
    private UserService userService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientValidator clientValidator;

    @Autowired
    private MessageSource messageSource;


    @RequestMapping(value = "/user/listClients", method = RequestMethod.GET)
    public String listClients(Model model) {
        model.addAttribute("client", new Client());
        model.addAttribute("listClients", this.clientService.getAll());

        return "user/client/listClients";
    }

    @RequestMapping(value = "/user/addClient", method = RequestMethod.GET)
    public String addClientGet(Model model) {
        Client client = new Client();
//        client.setName(" ");
        model.addAttribute("client", client);

        return "user/client/addClients";
    }

    @RequestMapping(value = "/user/addClient", method = RequestMethod.POST)
    public ModelAndView addClientsPost(@ModelAttribute(name = "client")Client client, BindingResult bindingResult,
                                  HttpServletRequest request, CookieLocaleResolver localeResolver) {
        ModelAndView modelAndView = new ModelAndView();

        if(confirmUser(request,localeResolver,modelAndView)){
            bindingResult.addError(null);
        }

        clientValidator.validate(client,bindingResult);

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("user/client/addClient");
            modelAndView.addObject("client", client);
            return modelAndView;
        }
        clientService.save(client);

        modelAndView.setViewName("user/client/listClients");
        modelAndView.addObject("listClients", this.clientService.getAll());
        modelAndView.addObject("client", new Client());

        return modelAndView;
    }

    @RequestMapping(value = "/user/editClient/{id}", method = RequestMethod.GET)
    public ModelAndView editClientGet(@PathVariable long id) {

        Client client = clientService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("client", client);

        return modelAndView;
    }

    @RequestMapping(value = "/user/editClient/{id}", method = RequestMethod.POST)
    public ModelAndView editClientPost(@ModelAttribute(name = "client")Client client, BindingResult bindingResult,
                                   @PathVariable long id, HttpServletRequest request, CookieLocaleResolver localeResolver) {

        ModelAndView modelAndView = new ModelAndView();

        Client clientFromDataBase = clientService.getById(id);

        if(!client.equals(clientFromDataBase)){
            if(confirmUser(request,localeResolver,modelAndView)){
                bindingResult.addError(null);
            }
            clientValidator.validate(client,bindingResult);

            if (bindingResult.hasErrors()) {
                modelAndView.setViewName("user/client/editClient");
                modelAndView.addObject("client", client);
                return modelAndView;
            }

            clientService.save(client);
        }
        modelAndView.setViewName("user/client/listClients");
        modelAndView.addObject("listClients", this.clientService.getAll());
        modelAndView.addObject("client", new Client());

        return modelAndView;
    }


    private boolean confirmUser(HttpServletRequest request,CookieLocaleResolver localeResolver,
                             ModelAndView modelAndView){
        User userFromDataBase = userService.findByUserName(request.getUserPrincipal().getName());

        String oldPassword = request.getParameter("oldPassword");

        userFromDataBase.setConfirmPassword(oldPassword);

        if((oldPassword == null)||
                (!userService.coincidencePassword(userFromDataBase.getConfirmPassword(),userFromDataBase.getPassword()))){
            modelAndView.addObject("errorConfirmPassword",
                    messageSource.getMessage("key.password.incorrect", null, localeResolver.resolveLocale(request)));
            return true;
        }
        return false;
    }
}
