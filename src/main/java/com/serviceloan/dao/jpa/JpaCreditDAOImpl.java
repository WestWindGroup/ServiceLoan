package com.serviceloan.dao.jpa;

import com.serviceloan.dao.CreditDAO;
import com.serviceloan.model.Credit;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

/**
 * JPA implementation of {@link CreditDAO} interface.
 *
 * @author Eugene Artemenko
 */

@Repository
public class JpaCreditDAOImpl implements CreditDAO {

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

    @Override
    public Collection<Credit> getAllCreditsClient(long id) {
        return null;
    }
}
