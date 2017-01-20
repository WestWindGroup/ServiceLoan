package com.serviceloan.dao;


import com.serviceloan.model.CreditDuration;

/**
 * Extension of {@link GenericDAO} interface for class {@link CreditDuration}.
 *
 * @author Eugene Artemenko
 */

public interface DurationDAO extends GenericDAO<CreditDuration,Long>{

    void remove(Long id);
}

