package com.serviceloan.service;

import com.serviceloan.model.Role;

import java.util.Collection;

/**
 * Service interface for class {@link Role}.
 *
 * @author Eugene Artemenko
 */
public interface RoleService {

    Role getById(Long id);

    Collection<Role> getAll();

    void save(Role role);

    void remove(Role role);

    void remove(long id);
}
