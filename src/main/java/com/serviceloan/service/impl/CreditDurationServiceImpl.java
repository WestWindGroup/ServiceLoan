package com.serviceloan.service.impl;

import com.serviceloan.dao.DurationDAO;
import com.serviceloan.model.CreditDuration;
import com.serviceloan.service.CreditDurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Implementation of {@link CreditDurationService} interface.
 *
 * @author Eugene Artemenko
 */

@Service
public class CreditDurationServiceImpl implements CreditDurationService {

    @Autowired
    private DurationDAO durationDao;


    @Override
    @Transactional
    public CreditDuration getById(Long id) {
        return durationDao.getById(id);
    }

    @Override
    @Transactional
    public Collection<CreditDuration> getAll() {
        return durationDao.getAll();
    }

    @Override
    @Transactional
    public void save(CreditDuration creditDuration) {
        durationDao.save(creditDuration);
    }

    @Override
    @Transactional
    public void remove(CreditDuration creditDuration) {
        durationDao.remove(creditDuration);
    }

    @Override
    @Transactional
    public void remove(long id) {
        durationDao.remove(id);
    }
}
