package com.serviceloan.dao.jpa;

import com.serviceloan.model.Role;
import com.serviceloan.service.RoleService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
        (locations =
                {"classpath:configTest/spring-config-root.xml", "classpath:configTest/mvc-dispatcher-servlet.xml"})
public class JpaRoleDAOImplTest {

    @Autowired
    private RoleService roleService;

    @Test
    @Transactional
    public void getById() throws Exception {
        Role role = this.roleService.getById(2l);
        Assert.assertTrue(role.getName().equals("ROLE_ADMIN"));
    }

    @Test
    @Transactional
    public void getAll() throws Exception {
        Collection<Role> roleList = this.roleService.getAll();
        int listRoleSize = roleList.size();

        Role role = new Role();
        role.setName("Test");

        this.roleService.save(role);

        roleList = this.roleService.getAll();
        Assert.assertTrue(roleList.size()==(listRoleSize + 1));
    }

    @Test
    @Transactional
    public void save() throws Exception {
        Collection<Role> roleList = this.roleService.getAll();

        Role role = new Role();
        role.setName("Test");

        this.roleService.save(role);

        Collection<Role> roleListN = this.roleService.getAll();
        Assert.assertTrue(roleListN.size()==(roleList.size() + 1));
    }

    @Test
    @Transactional
    public void removeById() throws Exception {


        this.roleService.remove(3l);

        Role role = roleService.getById(3l);

        Assert.assertTrue(role == null);
    }

    @Test
    @Transactional
    public void removeObject() throws Exception {

        Role role = new Role();
        role.setName("Test1");

        roleService.save(role);

        Collection<Role> roleListOne = this.roleService.getAll();

        roleService.remove(role);

        Collection<Role> roleListTwo = this.roleService.getAll();

        Assert.assertFalse(roleListTwo.size() == roleListOne.size());

    }


}