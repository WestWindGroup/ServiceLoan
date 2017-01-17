package com.serviceloan.service.impl;

import com.serviceloan.dao.RoleDAO;
import com.serviceloan.model.Role;
import com.serviceloan.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Implementation of {@link RoleService} interface.
 *
 * @author Eugene Artemenko
 */

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDAO roleDao;

    @Override
    @Transactional
    public Role getById(Long id) {
        return roleDao.getById(id);
    }

    @Override
    @Transactional
    public Collection<Role> getAll() {
        return roleDao.getAll();
    }

    @Override
    @Transactional
    public void save(Role role) {
        roleDao.save(role);
    }

    @Override
    @Transactional
    public void remove(Role role) {
        roleDao.remove(role);
    }

    @Override
    @Transactional
    public void remove(long id) {
        roleDao.remove(id);
    }
}