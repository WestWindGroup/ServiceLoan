package com.serviceloan.service.impl;

import com.serviceloan.dao.RateDAO;
import com.serviceloan.model.RateInterest;
import com.serviceloan.service.RateInterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Implementation of {@link RateInterestService} interface.
 *
 * @author Eugene Artemenko
 */

@Service
public class RateInterestServiceImpl implements RateInterestService{


    @Autowired
    private RateDAO rateDao;


    @Override
    @Transactional
    public RateInterest getById(Long id) {
        return rateDao.getById(id);
    }

    @Override
    @Transactional
    public Collection<RateInterest> getAll() {
        return rateDao.getAll();
    }

    @Override
    @Transactional
    public void save(RateInterest rateInterest) {
        rateDao.save(rateInterest);
    }

    @Override
    @Transactional
    public void remove(RateInterest rateInterest) {
        rateDao.remove(rateInterest);
    }

    @Override
    @Transactional
    public void remove(long id) {
        rateDao.remove(id);
    }
}
