package com.serviceloan.dao;

import com.serviceloan.model.User;

/**
 * Extension of {@link GenericDAO} interface for class {@link User}.
 *
 * @author Eugene Artemenko
 */
public interface UserDAO extends GenericDAO<User, Long> {

    User findByUserName(String username);
}
