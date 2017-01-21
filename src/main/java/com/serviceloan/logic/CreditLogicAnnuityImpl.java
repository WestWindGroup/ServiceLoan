package com.serviceloan.logic;



import com.serviceloan.model.Credit;
import com.serviceloan.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Service
public class CreditLogicAnnuityImpl implements CreditLogic {

    @Autowired
    private GregorianCalendar calendar;

    @Override
    public List<Payment> listPayments(Credit credit) {

        BigDecimal debt = credit.getAmount();
        BigDecimal rate = rate(credit.getPercent().getRate());
        BigDecimal pay = minPayment(credit). setScale(2, BigDecimal.ROUND_HALF_EVEN);
        List<Payment> payments = new ArrayList<>();
        for (int i = 1; i <= credit.getDuration().getDuration(); i++) {
            calendar.add(Calendar.MONTH, 1);
            Payment payment =  new Payment();
            BigDecimal ratePay = rateInPayment(rate, debt).setScale(2, BigDecimal.ROUND_HALF_EVEN);
            BigDecimal bodyPay = bodyInPayment(ratePay, pay).setScale(2, BigDecimal.ROUND_HALF_EVEN);
            debt = newDebt(ratePay, debt, pay);
            payment.setPaymentDate(calendar.getTime());
            payment.setBodyCredit(bodyPay);
            payment.setRatePayment(ratePay);
            payment.setAmountPayment(ratePay.add(bodyPay));

            payments.add(payment);
        }
        calendar = new GregorianCalendar();
        return payments;
    }

    @Override
    public BigDecimal minPayment(Credit credit) {
        int percentMax = 100;
        BigDecimal rate = new BigDecimal(String.valueOf(credit.getPercent().getRate() / percentMax)).
                setScale(5, BigDecimal.ROUND_HALF_EVEN);
        BigDecimal factor = factorСompute(rate, credit.getDuration().getDuration());

        return paymentMonth(factor, credit.getDebt()).setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }

    @Override
    public BigDecimal rateInPayment(Credit credit) {
        BigDecimal rate = rate(credit.getPercent().getRate()).multiply(credit.getDebt());
        return rate.setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }


    private BigDecimal rate(double interestRate) {
        int percentMax = 100;
        int countMonthInYear = 12;
        return new BigDecimal(String.valueOf(interestRate / percentMax / countMonthInYear)).
                setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }

    private BigDecimal rateInPayment(BigDecimal rate, BigDecimal amountOfCredit) {
        return rate.multiply(amountOfCredit).setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }

    @Override
    public BigDecimal bodyInPayment(BigDecimal rate, BigDecimal payment) {
        return payment.subtract(rate).setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }

    private BigDecimal newDebt(BigDecimal rate, BigDecimal debt, BigDecimal payment) {
        BigDecimal sbtract = payment.subtract(rate);
        return debt.subtract(sbtract).setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }

    private BigDecimal paymentMonth(BigDecimal factor, BigDecimal sumOfIndebtedness) {
        return factor.multiply(sumOfIndebtedness).setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }

    private BigDecimal factorСompute(BigDecimal rate, int durationOfContract) {
        int countMonthInYear = 12;
        BigDecimal i = rate.divide(new BigDecimal(countMonthInYear), BigDecimal.ROUND_HALF_EVEN);
        BigDecimal groupPow = new BigDecimal(1).add(i).pow(durationOfContract);
        BigDecimal up = i.multiply(groupPow);
        BigDecimal down = groupPow.subtract(new BigDecimal(1));
        return up.divide(down, BigDecimal.ROUND_HALF_EVEN).setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }

}
