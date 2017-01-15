package com.serviceloan.service.impl;

import com.serviceloan.model.Client;
import com.serviceloan.service.ClientService;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Implementation of {@link ClientService }interface
 *
 * @author Eugen Artemenko
 * @version 1.0
 */

@Service
public class ClientSeviceImpl implements ClientService {
    @Override
    public void save(Client client) {

    }

    @Override
    public Client getById(long id) {
        return null;
    }

    @Override
    public Client findByClientName(String lastName) {
        return null;
    }

    @Override
    public Collection<Client> getAll() {
        return null;
    }

    @Override
    public void remote(Client client) {

    }
}
