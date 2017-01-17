package com.serviceloan.service.impl;


import com.serviceloan.dao.CreditStatusDAO;
import com.serviceloan.model.CreditStatus;
import com.serviceloan.service.CreditStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Implementation of {@link CreditStatusService} interface.
 *
 * @author Eugene Artemenko
 */

@Service
public class CreditStatusServiceImpl implements CreditStatusService {

    @Autowired
    private CreditStatusDAO creditStatusDAO;

    @Override
    @Transactional
    public void save(CreditStatus creditStatus) {
        creditStatusDAO.save(creditStatus);
    }

    @Override
    @Transactional
    public CreditStatus getById(long id) {
        return creditStatusDAO.getById(id);
    }

    @Override
    @Transactional
    public Collection<CreditStatus> getAll() {
        return creditStatusDAO.getAll();
    }

    @Override
    @Transactional
    public void remove(CreditStatus creditStatus) {
        creditStatusDAO.remove(creditStatus);
    }

    @Override
    @Transactional
    public void remove(long id) {
        creditStatusDAO.remove(id);
    }
}
