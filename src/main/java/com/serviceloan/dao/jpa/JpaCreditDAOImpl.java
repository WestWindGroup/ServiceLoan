package com.serviceloan.dao.jpa;

import com.serviceloan.dao.CreditDAO;
import com.serviceloan.model.Credit;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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

    @SuppressWarnings("unchecked")
    @Override
    public Credit getById(Long id) {
        Query query = this.entityManager.createQuery("SELECT credit FROM  Credit credit " +
                "LEFT JOIN FETCH  credit.percent LEFT JOIN FETCH  credit.creditType " +
                "LEFT JOIN FETCH  credit.creditStatus LEFT JOIN FETCH  credit.duration " +
                "LEFT JOIN FETCH  credit.client WHERE credit.id =:id");
        query.setParameter("id", id);

        Credit credit = (Credit) query.getSingleResult();
        logger.info("Credit successfully loaded. Credit details: " + credit);

        return credit;
    }

    @Override
    public Collection<Credit> getAll() {
        Collection<Credit> result;
        Query query = this.entityManager.createQuery("SELECT credit FROM Credit credit");
        result = query.getResultList();

        for (Credit credit : result) {
            logger.info("Credit list: " + credit);
        }

        return result;
    }


    @Override
    public void save(Credit credit) {
        if (credit.getId() == null) {
            this.entityManager.persist(credit);
            logger.info("Credit successfully saved. Credit details: " + credit);
        } else {
            this.entityManager.merge(credit);
            logger.info("Credit successfully updated. Credit details: " + credit);
        }
    }

    @Override
    public void remove(Credit credit) {
        remove(credit.getId());
        logger.info("Credit successfully removed. Credit details: " + credit);
    }

    @Override
    public void remove(long id) {
        this.entityManager.remove(this.entityManager.getReference(Credit.class,id));
        logger.info("Credit successfully removed. Credit details: " + id);
    }

}
