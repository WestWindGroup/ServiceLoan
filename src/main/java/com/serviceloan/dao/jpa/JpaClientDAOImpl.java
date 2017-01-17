package com.serviceloan.dao.jpa;

import com.serviceloan.dao.ClientDAO;
import com.serviceloan.model.Client;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;

/**
 * JPA implementation of {@link ClientDAO} interface.
 *
 * @author Eugene Artemenko
 */

@Repository
public class JpaClientDAOImpl implements ClientDAO{

    @PersistenceContext
    private EntityManager entityManager;

    private final static Logger logger = Logger.getLogger(JpaRoleDAOImpl.class);

    @SuppressWarnings("unchecked")
    @Override
    public Client getById(Long id) {
        Query query = this.entityManager.createQuery("SELECT client FROM  Client client " +
                "WHERE client.id =:id");
        query.setParameter("id", id);

        Client client = (Client) query.getSingleResult();
        logger.info("Client successfully loaded. Client details: " + client);

        return client;
    }

    @Override
    public Collection getAll() {
        Collection<Client> result;
        Query query = this.entityManager.createQuery("SELECT client FROM Client client");
        result = query.getResultList();

        for (Client client : result) {
            logger.info("Client list: " + client);
        }

        return result;
    }


    @Override
    public void save(Client client) {
        if (client.getId() == null) {
            this.entityManager.persist(client);
            logger.info("Client successfully saved. Client details: " + client);
        } else {
            this.entityManager.merge(client);
            logger.info("Client successfully updated. Client details: " + client);
        }
    }

    @Override
    public void remove(Client client) {
        remove(client.getId());
        logger.info("Client successfully removed. Client details: " + client);
    }

    @Override
    public void remove(Long id) {
        this.entityManager.remove(this.entityManager.getReference(Client.class,id));
        logger.info("Client successfully removed. Client details: " + id);
    }

    @Override
    public Client findByName(String name) {
        try {
            Query query = this.entityManager.
                    createQuery("SELECT client FROM Client client WHERE client.name=:name", Client.class);
            query.setParameter("name", name);
            Client client = (Client) query.getSingleResult();
            return client;
        } catch (NoResultException e) {
            return null;
        }
    }
}
