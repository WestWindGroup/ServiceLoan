package com.serviceloan.service;


import com.serviceloan.model.User;

import java.util.Collection;

/**
 * service class for {@link User}
 *
 * @author Eugen Artemenko
 * @version 1.0
 */

public interface UserService {

    void save(User user);

    void update(User user);

    User getById(long id);

    User findByUserName(String username);

    Collection<User> getAll();

    boolean coincidencePassword(CharSequence rawPassword, String encodedPassword);

    void remove(User user);

    void remove(long id);
}
