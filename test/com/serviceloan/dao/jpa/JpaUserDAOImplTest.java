package com.serviceloan.dao.jpa;

import com.serviceloan.model.User;
import com.serviceloan.service.UserService;
import org.junit.*;
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
public class JpaUserDAOImplTest{

    @Autowired
    private UserService userService;

    @Test
    @Transactional
    public void getById() throws Exception {
        User user = this.userService.getById(2);
        Assert.assertTrue(user.getUsername().equals("user1"));
    }

    @Test
    @Transactional
    public void getAll() throws Exception {
        Collection<User> userList = this.userService.getAll();
        int listUserSize = userList.size();

        User user = new User();
        user.setUsername("Test");
        user.setFirstName("Test");
        user.setLastName("Test");
        user.setPassword("test");

        this.userService.save(user);

        userList = this.userService.getAll();
        Assert.assertTrue(userList.size()==(listUserSize + 1));
    }

    @Test
    @Transactional
    public void save() throws Exception {
        Collection<User> userList = this.userService.getAll();

        User user = new User();
        user.setUsername("Test");
        user.setFirstName("Test");
        user.setLastName("Test");
        user.setPassword("test");

        this.userService.save(user);

        Collection<User> userListN = this.userService.getAll();
        Assert.assertTrue(userListN.size()==(userList.size() + 1));
    }

    @Test
    @Transactional
    public void removeById() throws Exception {
        Collection<User> userListOne = this.userService.getAll();
        User one = new User();
        if(userListOne.iterator().hasNext()){
            one = userListOne.iterator().next();
        }

        this.userService.remove(1);

        User two = new User();
        Collection<User> userListTwo = this.userService.getAll();
        if(userListTwo.iterator().hasNext()){
            two = userListTwo.iterator().next();
        }

        Assert.assertFalse(one.getUsername().equals(two.getUsername()));
    }

    @Test
    @Transactional
    public void removeObject() throws Exception {

        User user = new User();
        user.setUsername("Test1");
        user.setFirstName("Test1");
        user.setLastName("Test1");
        user.setPassword("test1");

        userService.save(user);

        Collection<User> userListOne = this.userService.getAll();

        userService.remove(user);

        User test = userService.findByUserName("Test1");

        Collection<User> userListTwo = this.userService.getAll();

        Assert.assertFalse(userListTwo.size() == userListOne.size());

    }

    @Test
    @Transactional
    public void findByUserName() throws Exception {
        User user = userService.findByUserName("user2");

        Assert.assertTrue(user.getUsername().equals("user2"));
    }

}