package com.serviceloan.service;


import com.serviceloan.model.CreditStatus;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * service class for {@link CreditStatus}
 *
 * @author Eugen Artemenko
 * @version 1.0
 */

public interface CreditStatusService {

    void save(CreditStatus creditStatus);

    CreditStatus getById(long id);

    Collection<CreditStatus> getAll();

    void remove(CreditStatus creditStatus);

    void remove(long id);
}
