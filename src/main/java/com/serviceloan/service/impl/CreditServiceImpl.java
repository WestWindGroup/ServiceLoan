package com.serviceloan.service.impl;

import com.serviceloan.dao.CreditDAO;
import com.serviceloan.dao.CreditStatusDAO;
import com.serviceloan.model.Credit;
import com.serviceloan.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class CreditServiceImpl implements CreditService{

    @Autowired
    private CreditDAO creditDAO;

    @Override
    @Transactional
    public void save(Credit credit) {
        creditDAO.save(credit);
    }

    @Override
    @Transactional
    public Credit getById(long id) {
        return creditDAO.getById(id);
    }

    @Override
    @Transactional
    public Collection<Credit> getAll() {
        return creditDAO.getAll();
    }

    @Override
    @Transactional
    public Collection<Credit> getAllCreditsClient(long id){
        return creditDAO.getAllCreditsClient(id);
    }


    @Override
    @Transactional
    public void remove(Credit credit) {
        creditDAO.remove(credit);
    }

    @Override
    @Transactional
    public void remove(long id) {
        creditDAO.remove(id);
    }

}
