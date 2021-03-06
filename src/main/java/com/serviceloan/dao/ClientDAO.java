package com.serviceloan.dao;

import com.serviceloan.model.Client;

/**
 * Extension of {@link GenericDAO} interface for class {@link Client}.
 *
 * @author Eugene Artemenko
 */

public interface ClientDAO extends GenericDAO<Client,Long> {

    void remove(Long id);

    Client findByName(String name);
}
