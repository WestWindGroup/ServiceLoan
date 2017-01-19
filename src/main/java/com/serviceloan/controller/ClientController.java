package com.serviceloan.controller;

import com.serviceloan.model.Client;
import com.serviceloan.service.ClientService;
import com.serviceloan.service.CreditService;
import com.serviceloan.service.UserService;
import com.serviceloan.validator.ClientEditValidator;
import com.serviceloan.validator.ClientRegistrationValidator;
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

import java.io.IOException;

/**
 * Controller for Admin's pages
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
    private CreditService creditService;

    @Autowired
    private ClientRegistrationValidator registrationValidator;

    @Autowired
    private ClientEditValidator editValidator;

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

        return "user/client/addClient";
    }

    @RequestMapping(value = "/user/addClient", method = RequestMethod.POST)
    public ModelAndView addClientsPost(@ModelAttribute(name = "client")Client client,
                                       BindingResult bindingResult) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        client.setFirstName(new String(client.getFirstName().getBytes("ISO-8859-1"), "UTF-8"));
        client.setLastName(new String(client.getLastName().getBytes("ISO-8859-1"), "UTF-8"));

        registrationValidator.validate(client,bindingResult);

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

    @RequestMapping(value = "/user/pageClient/{id}", method = RequestMethod.GET)
    public ModelAndView pageClientGet(@PathVariable long id) {

        Client client = clientService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/client/pageClient");
        modelAndView.addObject("client", client);
        modelAndView.addObject("listCredits", client.getCreditSet());

        return modelAndView;
    }


    @RequestMapping(value = "/user/editClient/{id}", method = RequestMethod.GET)
    public ModelAndView editClientGet(@PathVariable long id) {

        Client client = clientService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/client/editClient");
        modelAndView.addObject("client", client);

        return modelAndView;
    }

    @RequestMapping(value = "/user/editClient/{id}", method = RequestMethod.POST)
    public ModelAndView editClientPost(@ModelAttribute(name = "client")Client client,BindingResult bindingResult,
                                       @PathVariable long id) throws IOException{

        ModelAndView modelAndView = new ModelAndView();
        client.setFirstName(new String(client.getFirstName().getBytes("ISO-8859-1"), "UTF-8"));
        client.setLastName(new String(client.getLastName().getBytes("ISO-8859-1"), "UTF-8"));

        Client clientFromDataBase = clientService.getById(id);

        if(!client.equals(clientFromDataBase)){
            editValidator.validate(client,bindingResult);
            client.setRegistrationDate(clientFromDataBase.getRegistrationDate());
            modelAndView.addObject("client", client);

            if (bindingResult.hasErrors()) {
                modelAndView.setViewName("user/client/editClient");
                return modelAndView;
            }
            clientService.save(client);
        }
        modelAndView.addObject("client", clientService.getById(id));
        modelAndView.setViewName("user/client/pageClient");
        return modelAndView;
    }

}
