package com.serviceloan.dao.jpa;

import com.serviceloan.dao.DurationDAO;
import com.serviceloan.model.CreditDuration;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;


/**
 * JPA implementation of {@link DurationDAO} interface.
 *
 * @author Eugene Artemenko
 */

@Repository
public class JpaDurationDAO implements DurationDAO {

    @PersistenceContext
    private EntityManager entityManager;

    private final static Logger logger = Logger.getLogger(JpaRateDAOImpl.class);

    @Override
    public CreditDuration getById(Long id) {

        try {
            Query query = this.entityManager.createQuery("SELECT creditDuration FROM  CreditDuration creditDuration " +
                    "LEFT JOIN FETCH  creditDuration.credits WHERE creditDuration.id =:id");
            query.setParameter("id", id);

            CreditDuration creditDuration = (CreditDuration) query.getSingleResult();
            logger.info("CreditDuration successfully loaded. CreditDuration details: " + creditDuration);

            return creditDuration;
        }catch (NoResultException e){
            return null;
        }
    }

    @Override
    public Collection<CreditDuration> getAll() {
        Collection<CreditDuration> result;
        Query query = this.entityManager.createQuery("SELECT rateInterest FROM CreditDuration rateInterest");
        result = query.getResultList();

        for (CreditDuration rateInterest : result) {
            logger.info("CreditDuration list: " + rateInterest);
        }

        return result;
    }

    @Override
    public void save(CreditDuration creditDuration) {
        if (creditDuration.getId() == null) {
            this.entityManager.persist(creditDuration);
            logger.info("CreditDuration successfully saved. CreditDuration details: " + creditDuration);
        } else {
            this.entityManager.merge(creditDuration);
            logger.info("CreditDuration successfully updated. CreditDuration details: " + creditDuration);
        }
    }

    @Override
    public void remove(Long id){
        this.entityManager.remove(this.entityManager.getReference(CreditDuration.class,id));
        logger.info("CreditDuration successfully removed. CreditDuration details: " + id);
    }

    @Override
    public void remove(CreditDuration creditDuration) {
        remove(creditDuration.getId());
        logger.info("CreditDuration successfully removed. CreditDuration details: " + creditDuration);
    }

}
