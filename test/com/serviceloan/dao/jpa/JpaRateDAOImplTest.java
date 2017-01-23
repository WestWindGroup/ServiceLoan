package com.serviceloan.dao.jpa;

import com.serviceloan.model.RateInterest;
import com.serviceloan.service.RateInterestService;
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
public class JpaRateDAOImplTest {

    @Autowired
    private RateInterestService rateService;

    @Test
    @Transactional
    public void getById() throws Exception {
        RateInterest rate = this.rateService.getById(2l);
        Assert.assertTrue(rate.getRate() == 31.50);
    }

    @Test
    @Transactional
    public void getAll() throws Exception {
        Collection<RateInterest> rateList = this.rateService.getAll();
        int listRateSize = rateList.size();

        RateInterest rate = new RateInterest();
        rate.setRate(5.5);

        this.rateService.save(rate);

        rateList = this.rateService.getAll();
        Assert.assertTrue(rateList.size()==(listRateSize + 1));
    }

    @Test
    @Transactional
    public void save() throws Exception {
        Collection<RateInterest> rateList = this.rateService.getAll();

        RateInterest rate = new RateInterest();
        rate.setRate(5.5);

        this.rateService.save(rate);

        Collection<RateInterest> rateListN = this.rateService.getAll();
        Assert.assertTrue(rateListN.size()==(rateList.size() + 1));
    }

    @Test
    @Transactional
    public void removeById() throws Exception {


        this.rateService.remove(3l);

        RateInterest rate = rateService.getById(3l);

        Assert.assertTrue(rate == null);
    }

    @Test
    @Transactional
    public void removeObject() throws Exception {

        RateInterest rate = new RateInterest();
        rate.setRate(5.5);

        rateService.save(rate);

        Collection<RateInterest> roleListOne = this.rateService.getAll();

        rateService.remove(rate);

        Collection<RateInterest> roleListTwo = this.rateService.getAll();

        Assert.assertFalse(roleListTwo.size() == roleListOne.size());

    }


}