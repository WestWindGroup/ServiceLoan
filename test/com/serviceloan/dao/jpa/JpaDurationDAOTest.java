package com.serviceloan.dao.jpa;

import com.serviceloan.model.CreditDuration;
import com.serviceloan.service.CreditDurationService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.Collection;

import static org.junit.Assert.*;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
        (locations =
                {"classpath:configTest/spring-config-root.xml", "classpath:configTest/mvc-dispatcher-servlet.xml"})
public class JpaDurationDAOTest {
    @Autowired
    private CreditDurationService durationService;

    @Test
    @Transactional
    public void getById() throws Exception {

        CreditDuration duration = this.durationService.getById(1l);
        Assert.assertTrue(duration.getDuration() == 12);
    }

    @Test
    @Transactional
    public void getAll() throws Exception {
        Collection<CreditDuration> list = this.durationService.getAll();
        int listSize = list.size();

        CreditDuration duration = new CreditDuration();
        duration.setDuration(72);

        this.durationService.save(duration);

        list = this.durationService.getAll();
        Assert.assertTrue(list.size()==(listSize + 1));
    }

    @Test
    @Transactional
    public void save() throws Exception {
        Collection<CreditDuration> list = this.durationService.getAll();

        CreditDuration payment = new CreditDuration();
        payment.setDuration(84);

        this.durationService.save(payment);

        Collection<CreditDuration> listN = this.durationService.getAll();
        Assert.assertTrue(listN.size()==(list.size() + 1));
    }

    @Test
    @Transactional
    public void removeById() throws Exception {

        CreditDuration payment = new CreditDuration();
        payment.setDuration(84);

        this.durationService.remove(3l);

        CreditDuration durationN = durationService.getById(3l);

        Assert.assertTrue(durationN == null);
    }

    @Test
    @Transactional
    public void removeObject() throws Exception {

        CreditDuration payment = new CreditDuration();
        durationService.save(payment);

        Collection<CreditDuration> listOne = this.durationService.getAll();

        durationService.remove(payment);

        Collection<CreditDuration> listTwo = this.durationService.getAll();

        Assert.assertFalse(listTwo.size() == listOne.size());

    }


}