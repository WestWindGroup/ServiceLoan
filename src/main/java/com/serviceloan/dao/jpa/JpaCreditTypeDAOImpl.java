package com.serviceloan.dao.jpa;


import com.serviceloan.dao.CreditTypeDAO;
import com.serviceloan.model.CreditType;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * JPA implementation of {@link CreditTypeDAO} interface.
 *
 * @author Eugene Artemenko
 */

@Repository
public class JpaCreditTypeDAOImpl implements CreditTypeDAO {

    @PersistenceContext
    private EntityManager entityManager;

    private final static Logger logger = Logger.getLogger(JpaCreditTypeDAOImpl.class);

    @SuppressWarnings("unchecked")
    @Override
    public CreditType getById(Long id) {
        try{
            Query query = this.entityManager.createQuery("SELECT creditType FROM  CreditType creditType " +
                    "LEFT JOIN FETCH  creditType.credits WHERE creditType.id =:id");
            query.setParameter("id", id);

            CreditType creditType = (CreditType) query.getSingleResult();
            logger.info("CreditType successfully loaded. CreditType details: " + creditType);

            return creditType;
        }catch (NoResultException e){
            return null;
        }

    }

    @Override
    public Collection<CreditType> getAll() {
        Collection<CreditType> result;
        Query query = this.entityManager.createQuery("SELECT creditType FROM CreditType creditType");
        result = query.getResultList();

        for (CreditType creditType : result) {
            logger.info("CreditType list: " + creditType);
        }

        return result;
    }

    @Override
    public void save(CreditType creditType) {
        if (creditType.getId() == null) {
            this.entityManager.persist(creditType);
            logger.info("CreditType successfully saved. CreditType details: " + creditType);
        } else {
            this.entityManager.merge(creditType);
            logger.info("CreditType successfully updated. CreditType details: " + creditType);
        }
    }

    @Override
    public void remove(Long id){
        this.entityManager.remove(this.entityManager.getReference(CreditType.class,id));
        logger.info("CreditType successfully removed. CreditType details: " + id);
    }

    @Override
    public void remove(CreditType creditType) {
        remove(creditType.getId());
        logger.info("CreditType successfully removed. CreditType details: " + creditType);
    }
}
