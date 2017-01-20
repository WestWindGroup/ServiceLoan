package com.serviceloan.dao;

import com.serviceloan.model.Payment;

/**
 * Extension of {@link GenericDAO} interface for class {@link Payment}.
 *
 * @author Eugene Artemenko
 */

public interface PaymentDAO extends GenericDAO<Payment,Long> {

    void remove(Long id);
}
