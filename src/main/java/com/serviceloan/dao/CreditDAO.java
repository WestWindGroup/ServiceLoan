package com.serviceloan.dao;

import com.serviceloan.model.Credit;

import java.util.Collection;

public interface CreditDAO extends GenericDAO<Credit,Long> {

    void remove(long id);
}
