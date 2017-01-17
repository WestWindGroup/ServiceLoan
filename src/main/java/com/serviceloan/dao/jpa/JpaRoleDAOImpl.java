package com.serviceloan.dao.jpa;

import com.serviceloan.dao.RoleDAO;
import com.serviceloan.model.Role;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;

/**
 * JPA implementation of {@link RoleDAO} interface.
 *
 * @author Eugene Artemenko
 */

@Repository
public class JpaRoleDAOImpl implements RoleDAO {

    @PersistenceContext
    private EntityManager entityManager;

    private final static Logger logger = Logger.getLogger(JpaRoleDAOImpl.class);

    @SuppressWarnings("unchecked")
    @Override
    public Role getById(Long id) {
        Query query = this.entityManager.createQuery("SELECT DISTINCT  role FROM  Role role LEFT JOIN FETCH  role.users WHERE role.id =:id");
        query.setParameter("id", id);

        Role role = (Role) query.getSingleResult();
        logger.info("Role successfully loaded. Role details: " + role);

        return role;
    }

    @Override
    public Collection<Role> getAll() {
        Collection<Role> result;
        Query query = this.entityManager.createQuery("SELECT role FROM Role role");
        result = query.getResultList();

        for (Role role : result) {
            logger.info("Role list: " + role);
        }

        return result;
    }

    @Override
    public void save(Role role) {
        if (role.getId() == null) {
            this.entityManager.persist(role);
            logger.info("Role successfully saved. Role details: " + role);
        } else {
            this.entityManager.merge(role);
            logger.info("Role successfully updated. Role details: " + role);
        }
    }

    @Override
    public void remove(Role role) {
        remove(role.getId());
        logger.info("Role successfully removed. Role details: " + role);
    }

    @Override
    public void remove(Long id){
        this.entityManager.remove(this.entityManager.getReference(Role.class,id));
        logger.info("Role successfully removed. Role details: " + id);
    }

}
