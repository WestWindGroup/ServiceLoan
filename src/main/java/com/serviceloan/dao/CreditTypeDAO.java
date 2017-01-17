package com.serviceloan.dao;


import com.serviceloan.model.CreditType;

/**
 * Extension of {@link GenericDAO} interface for class {@link CreditType}.
 *
 * @author Eugene Artemenko
 */

public interface CreditTypeDAO extends GenericDAO<CreditType,Long>{

    void remove(Long id);
}
