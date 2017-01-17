package com.serviceloan.dao;


import com.serviceloan.model.CreditStatus;

/**
 * Extension of {@link GenericDAO} interface for class {@link CreditStatus}.
 *
 * @author Eugene Artemenko
 */

public interface CreditStatusDAO extends GenericDAO<CreditStatus,Long> {

    void remove(Long id);
}
