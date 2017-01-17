package com.serviceloan.service;


import com.serviceloan.model.CreditType;

import java.util.Collection;

/**
 * service class for {@link CreditType}
 *
 * @author Eugen Artemenko
 * @version 1.0
 */

public interface CreditTypeService {

    void save(CreditType creditType);

    CreditType getById(long id);

    Collection<CreditType> getAll();

    void remove(CreditType creditType);

    void remove(long id);
}
