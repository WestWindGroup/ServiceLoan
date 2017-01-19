package com.serviceloan.controller;


import com.serviceloan.model.*;
import com.serviceloan.service.*;
import com.serviceloan.validator.CreditValidator;
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
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;


/**
 * Controller for Credit's pages
 *
 * @author Eugene Artemenko
 */
@Controller
public class CreditController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private CreditValidator creditValidator;

    @Autowired
    private CreditService creditService;

    @Autowired
    private RateInterestService interestService;

    @Autowired
    private CreditTypeService typeService;

    @Autowired
    private CreditStatusService statusService;

    @Autowired
    private MessageSource messageSource;


    @RequestMapping(value = "/user/addCredit/{id}", method = RequestMethod.GET)
    public String addCreditGet(Model model) {
//        Client client = clientService.getById(id);
//        model.addAttribute("credit", new Credit());
//        model.addAttribute("client", client);
        model.addAttribute("listRates", interestService.getAll());
        model.addAttribute("listTypes", typeService.getAll());
        model.addAttribute("listStatus", statusService.getAll());

        return "user/credit/addCredit";
    }

    @RequestMapping(value = "/user/addCredit/{id}", method = RequestMethod.POST)
    public ModelAndView addCreditPost(@PathVariable long id, HttpServletRequest request,
                                      CookieLocaleResolver localeResolver){
        ModelAndView modelAndView = new ModelAndView();
        String newCredit = request.getParameter("amountInput");
        Credit credit = new Credit();

        String percent = request.getParameter("percent");
        double rate = Double.parseDouble(percent);

        Collection<RateInterest> interests = interestService.getAll();
        for (RateInterest obj:interests) {
            if(rate == obj.getRate()){
                credit.setPercent(obj);
            }
        }

        String type = request.getParameter("type");
        Collection<CreditType> types = typeService.getAll();
        for (CreditType obj:types) {
            if(type.equals(obj.getType())){
                credit.setCreditType(obj);
            }
        }

        String status = request.getParameter("status");
        Collection<CreditStatus> statuses = statusService.getAll();
        for (CreditStatus obj:statuses) {
            if(status.equals(obj.getStatus())){
                credit.setCreditStatus(obj);
            }
        }

        if (!checkCorrectAmount(newCredit, credit, modelAndView, request, localeResolver)) {
            return modelAndView;
        }

        credit.setDebt(credit.getAmount());

        Client client = clientService.getById(id);
        client.getCreditSet().add(credit);
        clientService.save(client);
        modelAndView.setViewName("user/client/pageClient");
        modelAndView.addObject("listCredits", client.getCreditSet());
        modelAndView.addObject("client", client);

        return modelAndView;
    }

    private boolean checkCorrectAmount(String newCredit, Credit credit, ModelAndView modelAndView,
                                      HttpServletRequest request, CookieLocaleResolver localeResolver) {

        String min_amount = String.valueOf(creditValidator.getEnv().getProperty("key.min.amount"));
        String max_amount = String.valueOf(creditValidator.getEnv().getProperty("key.max.amount"));
        String msg = messageSource.getMessage(
                "key.credit", new String[]{min_amount, max_amount}, localeResolver.resolveLocale(request));
        if (!newCredit.equals("")) {
            if (creditValidator.validate(newCredit)) {
                credit.setAmount(new BigDecimal(newCredit));
            } else {
                modelAndView.addObject("errorAmount",
                        messageSource.getMessage("incorrectValue", null, localeResolver.resolveLocale(request)));
                modelAndView.setViewName("user/credit/addCredit");
                modelAndView.addObject("credit", credit);
                return false;
            }
        } else {
            modelAndView.addObject("errorRate",msg);
            modelAndView.setViewName("user/credit/addCredit");
            modelAndView.addObject("credit", credit);
            return false;
        }

        if (creditValidator.validate(credit.getAmount())) {
            return true;
        } else {
            modelAndView.addObject("errorRate",msg);
            modelAndView.setViewName("user/credit/addCredit");
            modelAndView.addObject("credit", credit);
            return false;
        }
    }
}
