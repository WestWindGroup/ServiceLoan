package com.serviceloan.dao;

import java.util.Collection;

/**
 * Generic DAO interface. Used as a base interface for all DAO classes.
 *
 * @author Eugene Artemenko
 */
public interface GenericDAO<T, ID> {

    T getById(ID id);

    Collection<T> getAll();

    void save(T entity);

    void remove(T entity);
}
