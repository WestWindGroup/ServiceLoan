package com.serviceloan.service;

import com.serviceloan.model.Credit;
import com.serviceloan.model.Payment;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

/**
 * service class for {@link Credit}
 *
 * @author Eugen Artemenko
 * @version 1.0
 */

public interface CreditService {

    void save(Credit credit);

    Credit getById(long id);

    Collection<Credit> getAll();

    void remove(Credit credit);

    void remove(long id);

    BigDecimal minPayment(Credit credit);

    List<Payment> listPayments(Credit credit);

    BigDecimal rateInPayment(Credit credit);
}
