package com.serviceloan.dao.jpa;


import com.serviceloan.dao.PaymentDAO;
import com.serviceloan.model.Payment;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;

/**
 * JPA implementation of {@link PaymentDAO} interface.
 *
 * @author Eugene Artemenko
 */

@Repository
public class JpaPaymentDAOImpl implements PaymentDAO {

    @PersistenceContext
    private EntityManager entityManager;

    private final static Logger logger = Logger.getLogger(JpaPaymentDAOImpl.class);

    @Override
    public Payment getById(Long id) {
        try {
            Query query = this.entityManager.createQuery("SELECT payment FROM  Payment payment " +
                    "LEFT JOIN FETCH  payment.credit WHERE payment.id =:id");
            query.setParameter("id", id);

            Payment payment = (Payment) query.getSingleResult();
            logger.info("Payment successfully loaded. Payment details: " + payment);

            return payment;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public Collection<Payment> getAll() {
        Collection<Payment> result;
        Query query = this.entityManager.createQuery("SELECT payment FROM Payment payment");
        result = query.getResultList();

        for (Payment payment : result) {
            logger.info("Payments list: " + payment);
        }

        return result;
    }

    @Override
    public void save(Payment payment) {
        if (payment.getId() == null) {
            this.entityManager.persist(payment);
            logger.info("Payment successfully saved. Payment details: " + payment);
        } else {
            this.entityManager.merge(payment);
            logger.info("Payment successfully updated. Payment details: " + payment);
        }

    }

    @Override
    public void remove(Long id) {
        this.entityManager.remove(this.entityManager.getReference(Payment.class,id));
        logger.info("Payment successfully removed. Payment details: " + id);
    }

    @Override
    public void remove(Payment payment) {
        remove(payment.getId());
        logger.info("Payment successfully removed. Payment details: " + payment);
    }
}
