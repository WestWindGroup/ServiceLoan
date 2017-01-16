package com.serviceloan.service.impl;

import com.serviceloan.model.Credit;
import com.serviceloan.service.CreditService;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CreditServiceImpl implements CreditService{
    @Override
    public void save(Credit credit) {

    }

    @Override
    public Credit getById(long id) {
        return null;
    }

    @Override
    public Collection<Credit> getAll() {
        return null;
    }

    @Override
    public void remote(Credit credit) {

    }

    @Override
    public Collection<Credit> getAllCreditsClient(long id) {
        return null;
    }
}
