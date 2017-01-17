package com.serviceloan.dao;


import com.serviceloan.model.RateInterest;

/**
 * Extension of {@link GenericDAO} interface for class {@link RateInterest}.
 *
 * @author Eugene Artemenko
 */
public interface RateDAO extends GenericDAO<RateInterest,Long>{

    void remove(Long id);
}
