package com.serviceloan.dao.jpa;

import com.serviceloan.model.Client;
import com.serviceloan.service.ClientService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

import static org.junit.Assert.*;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
        (locations =
                {"classpath:configTest/spring-config-root.xml", "classpath:configTest/mvc-dispatcher-servlet.xml"})
public class JpaClientDAOImplTest {
    @Autowired
    private ClientService clientService;

    @Test
    @Transactional
    public void getById() throws Exception {
        Client client = this.clientService.getById(2);
        Assert.assertTrue(client.getFirstName().equals("Test2"));
    }

    @Test
    @Transactional
    public void getAll() throws Exception {
        Collection<Client> list = this.clientService.getAll();
        int listSize = list.size();

        Client client = new Client();
        client.setFirstName("Test");
        client.setLastName("Test");

        this.clientService.save(client);

        list = this.clientService.getAll();
        Assert.assertTrue(list.size()==(listSize + 1));
    }

    @Test
    @Transactional
    public void save() throws Exception {
        Collection<Client> list = this.clientService.getAll();

        Client client = new Client();
        client.setFirstName("Test");
        client.setLastName("Test");

        this.clientService.save(client);

        Collection<Client> listN = this.clientService.getAll();
        Assert.assertTrue(listN.size()==(list.size() + 1));
    }


    @Test
    @Transactional
    public void removeObject() throws Exception {

        Client client = new Client();
        client.setFirstName("Test7");
        client.setLastName("Test7");

        clientService.save(client);

        Collection<Client> listOne = this.clientService.getAll();

        clientService.remove(client);

        Collection<Client> listTwo = this.clientService.getAll();

        Assert.assertFalse(listTwo.size() == listOne.size());

    }

    @Test
    @Transactional
    public void findByName() throws Exception {
        Client client = clientService.findByName("Test3");

        Assert.assertTrue(client.getFirstName().equals("Test3"));
    }


}