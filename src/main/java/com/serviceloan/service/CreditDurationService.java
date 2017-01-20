package com.serviceloan.service;


import com.serviceloan.model.CreditDuration;

import java.util.Collection;

/**
 * Service interface for class {@link CreditDuration}.
 *
 * @author Eugene Artemenko
 */
public interface CreditDurationService {

    CreditDuration getById(Long id);

    Collection<CreditDuration> getAll();

    void save(CreditDuration rateInterest);

    void remove(CreditDuration rateInterest);

    void remove(long id);
}

