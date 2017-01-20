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
    private CreditDurationService durationService;

    @Autowired
    private MessageSource messageSource;


    @RequestMapping(value = "/user/addCredit/{id}", method = RequestMethod.GET)
    public String addCreditGet(Model model,@PathVariable long id) {
        model.addAttribute("client", clientService.getById(id));
        model.addAttribute("listRates", interestService.getAll());
        model.addAttribute("listTypes", typeService.getAll());
        model.addAttribute("listStatus", statusService.getAll());
        model.addAttribute("listDuration", durationService.getAll());

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

        String durationStr = request.getParameter("duration");
        int duration = Integer.parseInt(durationStr);

        Collection<CreditDuration> durations = durationService.getAll();
        for (CreditDuration obj:durations) {
            if(duration == obj.getDuration()){
                credit.setDuration(obj);
            }
        }

        if (!creditValidator.checkCorrectAmount(newCredit, credit, modelAndView, request, localeResolver)) {
            modelAndView.addObject("client", clientService.getById(id));
            modelAndView.addObject("listRates", interestService.getAll());
            modelAndView.addObject("listTypes", typeService.getAll());
            modelAndView.addObject("listStatus", statusService.getAll());
            modelAndView.addObject("listDuration", durationService.getAll());
            return modelAndView;
        }

        credit.setDebt(credit.getAmount());

        Client client = clientService.getById(id);
        credit.setClient(client);
        creditService.save(credit);
        client = clientService.getById(id);
        modelAndView.setViewName("user/client/pageClient");
        modelAndView.addObject("client", client);
        modelAndView.addObject("listCredits", client.getCreditSet());


        return modelAndView;
    }

}
