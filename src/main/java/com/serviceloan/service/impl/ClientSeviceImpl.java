package com.serviceloan.service.impl;

import com.serviceloan.dao.ClientDAO;
import com.serviceloan.model.Client;
import com.serviceloan.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Implementation of {@link ClientService }interface
 *
 * @author Eugen Artemenko
 * @version 1.0
 */

@Service
public class ClientSeviceImpl implements ClientService {

    @Autowired
    private ClientDAO clientDAO;

    @Override
    @Transactional
    public void save(Client client) {
        clientDAO.save(client);
    }

    @Override
    @Transactional
    public Client getById(long id) {
        return clientDAO.getById(id);
    }

    @Override
    @Transactional
    public Client findByName(String lastName) {
        return clientDAO.findByName(lastName);
    }

    @Override
    @Transactional
    public Collection<Client> getAll() {
        return clientDAO.getAll();
    }

    @Override
    @Transactional
    public void remove(Client client) {
        clientDAO.remove(client);
    }

    @Override
    @Transactional
    public void remove(long id) {
        clientDAO.remove(id);
    }
}
