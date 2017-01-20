package com.serviceloan.service.impl;

import com.serviceloan.dao.PaymentDAO;
import com.serviceloan.model.Payment;
import com.serviceloan.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Implementation of {@link PaymentService }interface
 *
 * @author Eugen Artemenko
 * @version 1.0
 */

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentDAO paymentDAO;

    @Override
    public void save(Payment payment) {
        paymentDAO.save(payment);
    }

    @Override
    public Payment getById(long id) {
        return paymentDAO.getById(id);
    }

    @Override
    public Collection<Payment> getAll() {
        return paymentDAO.getAll();
    }

    @Override
    public void remove(Payment payment) {
        paymentDAO.remove(payment);
    }

    @Override
    public void remove(long id) {
        paymentDAO.remove(id);
    }
}
