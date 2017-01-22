package com.serviceloan.logic;

import com.serviceloan.model.Credit;
import com.serviceloan.model.Payment;

import java.math.BigDecimal;
import java.util.GregorianCalendar;
import java.util.List;

public interface CreditLogic {

    List<Payment> listPayments(Credit credit);

    BigDecimal minPayment(Credit credit);

    BigDecimal maxPayment(Credit credit);

    BigDecimal rateInPayment(Credit credit);

    BigDecimal bodyInPayment(BigDecimal rate, BigDecimal payment);

    default int daysInMonth(GregorianCalendar calendar) {

        int [] daysInMonths = {31,28,31,30,31,30,31,31,30,31,30,31};
        if(calendar.isLeapYear(calendar.get(GregorianCalendar.YEAR))){
            daysInMonths[1] += 1;
        }

        return daysInMonths[calendar.get(GregorianCalendar.MONTH)];
    }

    default int daysInYear(GregorianCalendar calendar) {
        int countDaysInYear = 365;
        int countDaysInLeapYear = 366;

        if(calendar.isLeapYear(calendar.get(GregorianCalendar.YEAR))){
            return countDaysInLeapYear;
        }else{
            return countDaysInYear;
        }
    }

}
