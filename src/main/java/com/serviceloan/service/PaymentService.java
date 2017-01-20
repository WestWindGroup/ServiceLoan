package com.serviceloan.service;


import com.serviceloan.model.Payment;

import java.util.Collection;

/**
 * service class for {@link Payment}
 *
 * @author Eugen Artemenko
 * @version 1.0
 */

public interface PaymentService {

    void save(Payment payment);

    Payment getById(long id);

    Collection<Payment> getAll();

    void remove(Payment payment);

    void remove(long id);
}
