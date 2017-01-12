package com.serviceloan.dao.jpa;

import com.serviceloan.dao.UserDAO;
import com.serviceloan.model.User;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;

/**
 * JPA implmentation of {@link UserDAO} interface.
 *
 * @author Eugene Artemenko
 */

@Repository
public class JpaUserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    private final static Logger logger = Logger.getLogger(JpaRoleDAOImpl.class);

    @Override
    public User getById(Long id) {
        Query query = this.entityManager.createQuery("SELECT DISTINCT user FROM  User user LEFT JOIN FETCH user.roles WHERE user.id =:id");
        query.setParameter("id", id);
        User user = (User) query.getSingleResult();

        logger.info("User successfully loaded. User details: " + user);

        return user;
    }

    @Override
    public Collection<User> getAll() {
        Collection<User> result;

        Query query = this.entityManager.createQuery("SELECT DISTINCT user FROM User user LEFT JOIN FETCH user.roles");
        result = query.getResultList();

        for (User user : result) {
            logger.info("User list: " + user);
        }

        return result;
    }

    @Override
    public void save(User user) {
        if (user.getId() == null) {
            this.entityManager.persist(user);
            logger.info("User successfully saved. User details: " + user);
        } else {
            this.entityManager.merge(user);
            logger.info("User successfully updated. User details: " + user);
        }
    }

    @Override
    public void remove(User user) {
        this.entityManager.remove(user);
        logger.info("User successfully removed. User details: " + user);
    }

    @Override
    public User findByUserName(String username) {

        try {
            Query query = this.entityManager.createQuery("SELECT user FROM User user WHERE user.username=:name", User.class);
            query.setParameter("name", username);
            User user = (User) query.getSingleResult();
            return user;
        } catch (NoResultException e) {
            return null;
        }
    }
}