package com.serviceloan.dao.jpa;


import com.serviceloan.dao.CreditStatusDAO;
import com.serviceloan.model.CreditStatus;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;

/**
 * JPA implementation of {@link CreditStatusDAO} interface.
 *
 * @author Eugene Artemenko
 */

@Repository
public class JpaCreditStatusDAOImpl implements CreditStatusDAO {


    @PersistenceContext
    private EntityManager entityManager;

    private final static Logger logger = Logger.getLogger(JpaRateDAOImpl.class);

    @SuppressWarnings("unchecked")
    @Override
    public CreditStatus getById(Long id) {
        Query query = this.entityManager.createQuery("SELECT creditStatus FROM  CreditStatus creditStatus " +
                "WHERE creditStatus.id =:id");
        query.setParameter("id", id);

        CreditStatus creditStatus = (CreditStatus) query.getSingleResult();
        logger.info("CreditStatus successfully loaded. CreditStatus details: " + creditStatus);

        return creditStatus;
    }

    @Override
    public Collection<CreditStatus> getAll() {
        Collection<CreditStatus> result;
        Query query = this.entityManager.createQuery("SELECT creditStatus FROM CreditStatus creditStatus");
        result = query.getResultList();

        for (CreditStatus creditStatus : result) {
            logger.info("RateInterest list: " + creditStatus);
        }

        return result;
    }

    @Override
    public void save(CreditStatus creditStatus) {
        if (creditStatus.getId() == null) {
            this.entityManager.persist(creditStatus);
            logger.info("CreditStatus successfully saved. CreditStatus details: " + creditStatus);
        } else {
            this.entityManager.merge(creditStatus);
            logger.info("CreditStatus successfully updated. CreditStatus details: " + creditStatus);
        }
    }

    @Override
    public void remove(CreditStatus creditStatus) {
        remove(creditStatus.getId());
        logger.info("CreditType successfully removed. CreditType details: " + creditStatus);
    }

    @Override
    public void remove(Long id) {
        this.entityManager.remove(this.entityManager.getReference(CreditStatus.class,id));
        logger.info("CreditStatus successfully removed. CreditStatus details: " + id);
    }
}
