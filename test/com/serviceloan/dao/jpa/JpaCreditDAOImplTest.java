package com.serviceloan.dao.jpa;

import com.serviceloan.model.Credit;
import com.serviceloan.service.CreditService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collection;

import static org.junit.Assert.*;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
        (locations =
                {"classpath:configTest/spring-config-root.xml", "classpath:configTest/mvc-dispatcher-servlet.xml"})
public class JpaCreditDAOImplTest {

    @Autowired
    private CreditService creditService;

    @Test
    @Transactional
    public void getById() throws Exception {
        Credit credit = this.creditService.getById(2);
        Assert.assertTrue(credit.getAmount().compareTo(new BigDecimal(10000))==0);
    }

    @Test
    @Transactional
    public void getAll() throws Exception {
        Collection<Credit> list = this.creditService.getAll();
        int listSize = list.size();

        Credit credit = new Credit();
        credit.setDebt(new BigDecimal(200));
        credit.setAmount(new BigDecimal(200));

        this.creditService.save(credit);

        list = this.creditService.getAll();
        Assert.assertTrue(list.size()==(listSize + 1));
    }

    @Test
    @Transactional
    public void save() throws Exception {
        Collection<Credit> list = this.creditService.getAll();

        Credit credit = new Credit();
        credit.setDebt(new BigDecimal(200));
        credit.setAmount(new BigDecimal(200));

        this.creditService.save(credit);

        Collection<Credit> listN = this.creditService.getAll();
        Assert.assertTrue(listN.size()==(list.size() + 1));
    }


    @Test
    @Transactional
    public void removeObject() throws Exception {

        Credit credit = new Credit();
        credit.setDebt(new BigDecimal(200));
        credit.setAmount(new BigDecimal(200));

        creditService.save(credit);

        Collection<Credit> listOne = this.creditService.getAll();

        creditService.remove(credit);

        Collection<Credit> listTwo = this.creditService.getAll();

        Assert.assertFalse(listTwo.size() == listOne.size());

    }

}