package com.serviceloan.dao.jpa;

import com.serviceloan.model.CreditStatus;
import com.serviceloan.service.CreditStatusService;
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
public class JpaCreditStatusDAOImplTest {

    @Autowired
    private CreditStatusService statusService;

    @Test
    @Transactional
    public void getById() throws Exception {
        CreditStatus creditStatus = this.statusService.getById(2);
        Assert.assertTrue(creditStatus.getStatus().equals("status2"));
    }

    @Test
    @Transactional
    public void getAll() throws Exception {
        Collection<CreditStatus> creditStatusList = this.statusService.getAll();
        int listSize = creditStatusList.size();

        CreditStatus creditStatus = new CreditStatus();
        creditStatus.setStatus("Test");

        this.statusService.save(creditStatus);

        creditStatusList = this.statusService.getAll();
        Assert.assertTrue(creditStatusList.size()==(listSize + 1));
    }

    @Test
    @Transactional
    public void save() throws Exception {
        Collection<CreditStatus> roleList = this.statusService.getAll();

        CreditStatus creditType = new CreditStatus();
        creditType.setStatus("Test");

        this.statusService.save(creditType);

        Collection<CreditStatus> listN = this.statusService.getAll();
        Assert.assertTrue(listN.size()==(roleList.size() + 1));
    }

    @Test
    @Transactional
    public void removeById() throws Exception {

        CreditStatus creditStatus = new CreditStatus();
        creditStatus.setId(3l);
        creditStatus.setStatus("Test5");
        statusService.save(creditStatus);

        this.statusService.remove(3l);

        CreditStatus role = statusService.getById(3l);

        Assert.assertTrue(role == null);
    }

    @Test
    @Transactional
    public void removeObject() throws Exception {

        CreditStatus creditStatus = new CreditStatus();
        creditStatus.setStatus("Test1");

        statusService.save(creditStatus);

        Collection<CreditStatus> listOne = this.statusService.getAll();

        statusService.remove(creditStatus);

        Collection<CreditStatus> listTwo = this.statusService.getAll();

        Assert.assertFalse(listTwo.size() == listOne.size());

    }

}