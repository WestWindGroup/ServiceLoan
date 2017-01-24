package com.serviceloan.dao;

import com.serviceloan.model.Credit;


public interface CreditDAO extends GenericDAO<Credit,Long> {

    void remove(long id);
}
