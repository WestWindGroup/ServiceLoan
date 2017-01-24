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
import java.util.List;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
        (locations =
                {"classpath:configTest/spring-config-root.xml", "classpath:configTest/mvc-dispatcher-servlet.xml"})
public class CreditLogicAnnuityImplTest {

    @Autowired
    private Credit credit;

    @Autowired
    private CreditLogicAnnuityImpl creditLogic;

    @Before
    public void setUp() throws Exception {
        RateInterest rateInterest = new RateInterest();
        rateInterest.setRate(10);
        credit.setAmount(new BigDecimal("1000"));
        credit.setDebt(new BigDecimal("1000"));
        CreditDuration creditDuration = new CreditDuration();
        creditDuration.setDuration(12);
        credit.setDuration(creditDuration);

    }

    @Test
    public void listPaymentsTest() throws Exception {
        RateInterest rateInterest = new RateInterest();
        rateInterest.setRate(10);
        credit.setPercent(rateInterest);
        List<Payment> listPayments = creditLogic.listPayments(credit);
        Assert.assertTrue(listPayments.size() == 12);
    }

    @Test
    public void minPaymentTest() throws Exception {
        int percentMax = 100;
        RateInterest rateInterest = new RateInterest();
        rateInterest.setRate(10);
        credit.setPercent(rateInterest);
        BigDecimal rate = new BigDecimal(String.valueOf(credit.getPercent().getRate() / percentMax)).
                setScale(5, BigDecimal.ROUND_HALF_EVEN);
        int countMonthInYear = 12;

        BigDecimal i = rate.divide(new BigDecimal(countMonthInYear), BigDecimal.ROUND_HALF_EVEN);
        BigDecimal groupPow = new BigDecimal(1).add(i).pow(credit.getDuration().getDuration());
        BigDecimal up = i.multiply(groupPow);
        BigDecimal down = groupPow.subtract(new BigDecimal(1));

        BigDecimal factor = up.divide(down, BigDecimal.ROUND_HALF_EVEN).setScale(2, BigDecimal.ROUND_HALF_EVEN);

        BigDecimal minPay = factor.multiply(credit.getDebt()).setScale(2, BigDecimal.ROUND_HALF_EVEN);
        BigDecimal helpValue = creditLogic.minPayment(credit);

        Assert.assertTrue(minPay.compareTo(helpValue) == 0);
    }

    @Test
    public void maxPaymentTest() {
        int percentMax = 100;
        int countMonthInYear = 12;
        RateInterest rateInterest = new RateInterest();
        rateInterest.setRate(10);
        credit.setPercent(rateInterest);
        BigDecimal rate =
                new BigDecimal(String.valueOf(credit.getPercent().getRate() / percentMax / countMonthInYear)).
                setScale(2, BigDecimal.ROUND_HALF_EVEN);
        rate = rate.multiply(credit.getDebt());
        BigDecimal maxPay = rate.setScale(2, BigDecimal.ROUND_HALF_EVEN).add( credit.getDebt() );
        BigDecimal helpValue = creditLogic.maxPayment(credit);

        Assert.assertTrue(maxPay.compareTo(helpValue) == 0);
    }


    @Test
    public void rateInPaymentTest() throws Exception {
        int percentMax = 100;
        int countMonthInYear = 12;
        RateInterest rateInterest = new RateInterest();
        rateInterest.setRate(10);
        credit.setPercent(rateInterest);
        BigDecimal rate =
                new BigDecimal(String.valueOf(credit.getPercent().getRate() / percentMax / countMonthInYear)).
                        setScale(2, BigDecimal.ROUND_HALF_EVEN);
        rate = rate.multiply(credit.getDebt()).setScale(2, BigDecimal.ROUND_HALF_EVEN);
        BigDecimal helpValue = creditLogic.rateInPayment(credit);
        Assert.assertTrue(rate.compareTo(helpValue) == 0);

    }

}