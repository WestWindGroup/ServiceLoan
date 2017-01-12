package com.serviceloan.service;


import com.serviceloan.model.CreditType;
import com.serviceloan.model.RateInterest;

import java.util.Collection;

/**
 * service class for {@link RateInterest}
 *
 * @author Eugen Artemenko
 * @version 1.0
 */

public interface RateInterestService {

    void save(RateInterest rateInterest);

    RateInterest getById(long id);

    Collection<RateInterest> getAll();

    void remote(RateInterest rateInterest);
}
