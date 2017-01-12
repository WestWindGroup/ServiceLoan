package com.serviceloan.service;

import com.serviceloan.model.Credit;

import java.util.Collection;

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

    void remote(Credit credit);
}
