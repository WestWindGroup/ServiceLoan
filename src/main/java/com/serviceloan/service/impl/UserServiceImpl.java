

package com.serviceloan.service.impl;

import com.serviceloan.dao.RoleDAO;
import com.serviceloan.dao.UserDAO;
import com.serviceloan.model.Role;
import com.serviceloan.model.User;
import com.serviceloan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
/**
 * Implementation of {@link UserService }interface
 *
 * @author Eugen Artemenko
 * @version 1.0
 */


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDao;

    @Autowired
    private RoleDAO roleDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.getById(1L));
        user.setRoles(roles);
        userDao.save(user);
    }

    @Override
    @Transactional
    public void update(User user) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.getById(1L));
        user.setRoles(roles);
        userDao.save(user);
    }

    @Override
    @Transactional
    public User getById(long id){
        User user = userDao.getById(id);
        return user;
    }

    @Override
    @Transactional
    public User findByUserName(String username) {
        return userDao.findByUserName(username);
    }

    @Override
    @Transactional
    public Collection<User> getAll() {
        return userDao.getAll();
    }

    @Override
    @Transactional
    public boolean coincidencePassword(CharSequence rawPassword, String encodedPassword) {
        return bCryptPasswordEncoder.matches(rawPassword,encodedPassword);
    }

    @Override
    @Transactional
    public void remove(User user) {
        userDao.remove(user);
    }

    @Override
    @Transactional
    public void remove(long id) {
        userDao.remove(id);
    }

}