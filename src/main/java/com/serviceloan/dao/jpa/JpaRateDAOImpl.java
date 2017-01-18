package com.serviceloan.dao.jpa;


import com.serviceloan.dao.RateDAO;
import com.serviceloan.model.RateInterest;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;

/**
 * JPA implementation of {@link RateDAO} interface.
 *
 * @author Eugene Artemenko
 */

@Repository
public class JpaRateDAOImpl implements RateDAO{

    @PersistenceContext
    private EntityManager entityManager;

    private final static Logger logger = Logger.getLogger(JpaRateDAOImpl.class);

    @Override
    public RateInterest getById(Long id) {

        try {
            Query query = this.entityManager.createQuery("SELECT rateInterest FROM  RateInterest rateInterest " +
                    "LEFT JOIN FETCH  rateInterest.credits WHERE rateInterest.id =:id");
            query.setParameter("id", id);

            RateInterest rateInterest = (RateInterest) query.getSingleResult();
            logger.info("RateInterest successfully loaded. RateInterest details: " + rateInterest);

            return rateInterest;
        }catch (NoResultException e){
            return null;
        }
    }

    @Override
    public Collection<RateInterest> getAll() {
        Collection<RateInterest> result;
        Query query = this.entityManager.createQuery("SELECT rateInterest FROM RateInterest rateInterest");
        result = query.getResultList();

        for (RateInterest rateInterest : result) {
            logger.info("RateInterest list: " + rateInterest);
        }

        return result;
    }

    @Override
    public void save(RateInterest rateInterest) {
        if (rateInterest.getId() == null) {
            this.entityManager.persist(rateInterest);
            logger.info("RateInterest successfully saved. RateInterest details: " + rateInterest);
        } else {
            this.entityManager.merge(rateInterest);
            logger.info("RateInterest successfully updated. RateInterest details: " + rateInterest);
        }
    }

    @Override
    public void remove(Long id){
        this.entityManager.remove(this.entityManager.getReference(RateInterest.class,id));
        logger.info("RateInterest successfully removed. RateInterest details: " + id);
    }

    @Override
    public void remove(RateInterest rateInterest) {
        remove(rateInterest.getId());
        logger.info("RateInterest successfully removed. RateInterest details: " + rateInterest);
    }

}
