package com.serviceloan.service;


import com.serviceloan.model.Client;

import java.util.Collection;

/**
 * service class for {@link Client}
 *
 * @author Eugen Artemenko
 * @version 1.0
 */

public interface ClientService {

    void save(Client client);

    Client getById(long id);

    Client findByName(String lastName);

    Collection<Client> getAll();

    void remove(Client client);

    void remove(long id);

}
