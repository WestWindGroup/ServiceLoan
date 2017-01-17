package com.serviceloan.service;


import com.serviceloan.model.RateInterest;

import java.util.Collection;

/**
 * Service interface for class {@link RateInterest}.
 *
 * @author Eugene Artemenko
 */
public interface RateInterestService {

    RateInterest getById(Long id);

    Collection<RateInterest> getAll();

    void save(RateInterest rateInterest);

    void remove(RateInterest rateInterest);

    void remove(long id);
}
