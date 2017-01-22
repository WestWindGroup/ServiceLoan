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
public class CreditLogicBalanceDecreaseImpl implements CreditLogic {

    @Autowired
    private GregorianCalendar calendar;

    @Override
    public List<Payment> listPayments(Credit credit) {
        BigDecimal debt = credit.getAmount();
        BigDecimal convertDuration = new BigDecimal(credit.getDuration().getDuration());
        BigDecimal bodyPay = credit.getAmount().divide(convertDuration,BigDecimal.ROUND_HALF_EVEN);
        List<Payment> payments = new ArrayList<>();
        for (int i = 1; i <= credit.getDuration().getDuration(); i++) {
            calendar.add(Calendar.MONTH, 1);
            Payment payment =  new Payment();
            BigDecimal ratePay = ratePayment(credit.getPercent().getRate(),debt);
            payment.setPaymentDate(calendar.getTime());
            payment.setBodyCredit(bodyPay);
            payment.setRatePayment(ratePay);
            payment.setAmountPayment(ratePay.add(bodyPay));
            debt = debt.subtract(bodyPay);
            payments.add(payment);
        }
        calendar = new GregorianCalendar();
        return payments;
    }

    @Override
    public BigDecimal minPayment(Credit credit) {
        BigDecimal convertDuration = new BigDecimal(String.valueOf(credit.getDuration().getDuration()));
        BigDecimal percentCredit = rateInPayment(credit);
        BigDecimal bodyPayment = credit.getAmount().divide(convertDuration, BigDecimal.ROUND_HALF_EVEN);
        if(bodyPayment.compareTo(credit.getDebt()) == -1){
            return percentCredit.add(bodyPayment).setScale(2, BigDecimal.ROUND_HALF_EVEN);
        }else{
            return percentCredit.add(credit.getDebt()).setScale(2, BigDecimal.ROUND_HALF_EVEN);
        }
    }

    @Override
    public BigDecimal maxPayment(Credit credit) {
        BigDecimal amount = rateInPayment(credit).add( credit.getDebt() );
        return amount;
    }

    @Override
    public BigDecimal rateInPayment(Credit credit) {
        return ratePayment(credit.getPercent().getRate(),credit.getDebt());
    }

    @Override
    public BigDecimal bodyInPayment(BigDecimal rate, BigDecimal payment) {
        return payment.subtract(rate).setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }

    private BigDecimal ratePayment(double interestRate,BigDecimal sumOfIndebtedness){
        int countDayOnMonth = daysInMonth(calendar);
        String helpValue = String.valueOf(countDayOnMonth * interestRate);
        BigDecimal big = sumOfIndebtedness.multiply(new BigDecimal(helpValue));
        big = big.divide(new BigDecimal(100));
        return big.divide(new BigDecimal(daysInYear(calendar)), BigDecimal.ROUND_HALF_EVEN).
                setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }

}