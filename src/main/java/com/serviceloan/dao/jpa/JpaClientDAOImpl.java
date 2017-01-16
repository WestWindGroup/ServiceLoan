package com.serviceloan.dao.jpa;

import com.serviceloan.dao.ClientDAO;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

    @Override
    public Object getById(Object o) {
        return null;
    }

    @Override
    public Collection getAll() {
        return null;
    }

    @Override
    public void save(Object entity) {

    }

    @Override
    public void remove(Object entity) {

    }
}
