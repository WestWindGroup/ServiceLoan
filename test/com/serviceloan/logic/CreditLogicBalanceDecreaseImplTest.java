package com.serviceloan.logic;

import com.serviceloan.model.Credit;
import com.serviceloan.model.CreditDuration;
import com.serviceloan.model.Payment;
import com.serviceloan.model.RateInterest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigDecimal;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.*;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
        (locations =
                {"classpath:configTest/spring-config-root.xml", "classpath:configTest/mvc-dispatcher-servlet.xml"})
public class CreditLogicBalanceDecreaseImplTest {

    @Autowired
    private Credit credit;

    @Autowired
    private CreditLogicBalanceDecreaseImpl creditLogic;

    private RateInterest rateInterest;

    @Before
    public void setUp() throws Exception {
        rateInterest = new RateInterest();
        rateInterest.setRate(10);
        credit.setAmount(new BigDecimal("1000"));
        credit.setDebt(new BigDecimal("1000"));
        CreditDuration creditDuration = new CreditDuration();
        creditDuration.setDuration(12);
        credit.setDuration(creditDuration);

    }

    @Test
    public void listPaymentsTest() throws Exception {
        credit.setPercent(rateInterest);
        List<Payment> listPayments = creditLogic.listPayments(credit);
        Assert.assertTrue(listPayments.size() == 12);
    }

    @Test
    public void minPaymentTest() throws Exception {
        credit.setPercent(rateInterest);

        GregorianCalendar calendar = new GregorianCalendar();
        String strHelp = String.valueOf(creditLogic.daysInMonth(calendar) * credit.getPercent().getRate()/100);
        BigDecimal rateHelp = new BigDecimal(strHelp);

        BigDecimal duration = new BigDecimal(String.valueOf(credit.getDuration().getDuration()));
        BigDecimal body = credit.getAmount().divide(duration,BigDecimal.ROUND_HALF_EVEN);

        BigDecimal rate = credit.getDebt().multiply(rateHelp);
        rate = rate.divide(new BigDecimal(creditLogic.daysInYear(calendar)),BigDecimal.ROUND_HALF_EVEN);
        BigDecimal helpValue = body.add(rate);
        BigDecimal minPayment = creditLogic.minPayment(credit);
        System.out.println(helpValue + "  " + minPayment);
        Assert.assertTrue(minPayment.compareTo(helpValue) == 0);
    }

    @Test
    public void rateInPaymentTest() throws Exception {
        rateInterest.setRate(0);
        credit.setPercent(rateInterest);
        BigDecimal minPayment = creditLogic.rateInPayment(credit);
        Assert.assertTrue(minPayment.compareTo(new BigDecimal("0")) == 0);
        rateInterest.setRate(1);
        credit.setPercent(rateInterest);
        minPayment = creditLogic.rateInPayment(credit);
        Assert.assertTrue(minPayment.compareTo(new BigDecimal("0")) == 1);
    }

}