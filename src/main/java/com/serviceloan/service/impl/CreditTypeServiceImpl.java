package com.serviceloan.service.impl;


import com.serviceloan.dao.CreditTypeDAO;
import com.serviceloan.model.CreditType;
import com.serviceloan.service.CreditTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Implementation of {@link CreditTypeService }interface
 *
 * @author Eugen Artemenko
 * @version 1.0
 */

@Service
public class CreditTypeServiceImpl implements CreditTypeService {

    @Autowired
    private CreditTypeDAO creditTypeDAO;

    @Override
    @Transactional
    public void save(CreditType creditType) {
        creditTypeDAO.save(creditType);
    }

    @Override
    @Transactional
    public CreditType getById(long id) {
        return creditTypeDAO.getById(id);
    }

    @Override
    @Transactional
    public Collection<CreditType> getAll() {
        return creditTypeDAO.getAll();
    }

    @Override
    @Transactional
    public void remove(CreditType creditType) {
        creditTypeDAO.remove(creditType);
    }

    @Override
    @Transactional
    public void remove(long id) {
        creditTypeDAO.remove(id);
    }
}
