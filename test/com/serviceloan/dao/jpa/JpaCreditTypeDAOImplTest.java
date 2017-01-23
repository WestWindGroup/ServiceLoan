package com.serviceloan.dao.jpa;

import com.serviceloan.model.CreditType;
import com.serviceloan.service.CreditTypeService;
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
public class JpaCreditTypeDAOImplTest {

    @Autowired
    private CreditTypeService typeService;

    @Test
    @Transactional
    public void getById() throws Exception {
        CreditType creditType = this.typeService.getById(2);
        Assert.assertTrue(creditType.getType().equals("type2"));
    }

    @Test
    @Transactional
    public void getAll() throws Exception {
        Collection<CreditType> creditTypeList = this.typeService.getAll();
        int listCreditTypeSize = creditTypeList.size();

        CreditType creditType = new CreditType();
        creditType.setType("Test");

        this.typeService.save(creditType);

        creditTypeList = this.typeService.getAll();
        Assert.assertTrue(creditTypeList.size()==(listCreditTypeSize + 1));
    }

    @Test
    @Transactional
    public void save() throws Exception {
        Collection<CreditType> roleList = this.typeService.getAll();

        CreditType creditType = new CreditType();
        creditType.setType("Test");

        this.typeService.save(creditType);

        Collection<CreditType> listN = this.typeService.getAll();
        Assert.assertTrue(listN.size()==(roleList.size() + 1));
    }

    @Test
    @Transactional
    public void removeById() throws Exception {
        CreditType creditType = new CreditType();
        creditType.setType("Test5");
        typeService.save(creditType);

        this.typeService.remove(9l);

        CreditType type = typeService.getById(9l);

        Assert.assertTrue(type == null);
    }

    @Test
    @Transactional
    public void removeObject() throws Exception {

        CreditType creditType = new CreditType();
        creditType.setType("Test1");

        typeService.save(creditType);

        Collection<CreditType> listOne = this.typeService.getAll();

        typeService.remove(creditType);

        Collection<CreditType> listTwo = this.typeService.getAll();

        Assert.assertFalse(listTwo.size() == listOne.size());

    }


}