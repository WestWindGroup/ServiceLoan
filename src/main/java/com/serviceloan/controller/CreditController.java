package com.serviceloan.controller;


import com.serviceloan.model.*;
import com.serviceloan.service.*;
import com.serviceloan.validator.CreditValidator;
import com.serviceloan.validator.PaymentValidator;
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
    private PaymentValidator paymentValidator;

    @Autowired
    private PaymentService paymentService;

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

    @RequestMapping(value = "/user/pageCredit/{client_id}/{credit_id}",method = RequestMethod.GET)
    public String pageCreditGet(Model model,@PathVariable("client_id") long client_id,
                                @PathVariable("credit_id") long credit_id){
        Credit credit = creditService.getById(credit_id);
        model.addAttribute("client",clientService.getById(client_id));
        model.addAttribute("credit",credit);
        model.addAttribute("listPayments",credit.getPayments());
        return "user/credit/pageCredit";
    }


    @RequestMapping(value = "/user/listPayments/{client_id}/{credit_id}",method = RequestMethod.GET)
    public String pageListPaymentsGet(Model model,@PathVariable("client_id") long client_id,
                                @PathVariable("credit_id") long credit_id){

        Credit credit = creditService.getById(credit_id);
        model.addAttribute("client",clientService.getById(client_id));
        model.addAttribute("credit",credit);
        model.addAttribute("listPayments",credit.getPayments());

        return "user/credit/listPayments";
    }

    @RequestMapping(value = "/user/makePayment/{client_id}/{credit_id}", method = RequestMethod.GET)
    public String madePaymentGet(Model model,@PathVariable("client_id") long client_id,
                                 @PathVariable("credit_id") long credit_id) {

        Credit credit = creditService.getById(credit_id);
        model.addAttribute("minPayment",creditService.minPayment(credit));
        model.addAttribute("client",clientService.getById(client_id));
        model.addAttribute("credit",credit);

        return "user/credit/makePayment";
    }

    @RequestMapping(value = "/user/makePayment/{client_id}/{credit_id}", method = RequestMethod.POST)
    public ModelAndView madePaymentPost(@PathVariable("client_id") long client_id,
                                        @PathVariable("credit_id") long credit_id,
                                        HttpServletRequest request,
                                        CookieLocaleResolver localeResolver){
        ModelAndView modelAndView = new ModelAndView();
        Credit credit = creditService.getById(credit_id);
        String paymentInput = request.getParameter("paymentInput");
        BigDecimal minPay = creditService.minPayment(credit);
        String msg =  messageSource.
                getMessage("key.credit", new String[]{minPay.toString(), credit.getDebt().toString()},
                        localeResolver.resolveLocale(request));

        modelAndView.addObject("minPayment",minPay);
        modelAndView.addObject("client",clientService.getById(client_id));
        modelAndView.addObject("credit", credit);
        modelAndView.addObject("errorPayment",msg);
        modelAndView.setViewName("user/credit/makePayment");

        if (!paymentValidator.checkCorrect(paymentInput)){
            return modelAndView;
        }
        paymentInput = paymentValidator.replacedOnComma(paymentInput);
        BigDecimal amountPay = new BigDecimal(paymentInput);

        if (!paymentValidator.checkCorrectAmount(amountPay,minPay,credit.getDebt())){
            return modelAndView;
        }
        Payment payment = new Payment();
        payment.setAmountPayment(amountPay);

        BigDecimal rate = creditService.rateInPayment(credit);
        payment.setRatePayment(rate);

        BigDecimal body = creditService.bodyInPayment(credit,rate,amountPay);
        payment.setBodyCredit(body);

        credit.setDebt(creditService.bodyInPayment(credit,body,credit.getDebt()));
        credit.getPayments().add(payment);
        creditService.save(credit);

        Client client = clientService.getById(client_id);

        modelAndView.clear();
        modelAndView.setViewName("user/credit/pageCredit");
        modelAndView.addObject("client", client);
        modelAndView.addObject("credit", credit);
        modelAndView.addObject("listPayments",credit.getPayments());


        return modelAndView;
    }


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
