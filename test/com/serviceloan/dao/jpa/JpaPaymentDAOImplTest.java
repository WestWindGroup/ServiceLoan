package com.serviceloan.dao.jpa;

import com.serviceloan.model.Payment;
import com.serviceloan.service.PaymentService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
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
public class JpaPaymentDAOImplTest {

    @Autowired
    private PaymentService paymentService;

    @Test
    @Transactional
    public void getById() throws Exception {

        Payment paymentNew = this.paymentService.getById(1l);
        Assert.assertTrue(paymentNew.getBodyCredit().compareTo(new BigDecimal(100))==0);
    }

    @Test
    @Transactional
    public void getAll() throws Exception {
        Collection<Payment> list = this.paymentService.getAll();
        int listSize = list.size();

        Payment payment = new Payment();
        payment.setRatePayment(new BigDecimal(200));
        payment.setAmountPayment(new BigDecimal(300));
        payment.setBodyCredit(new BigDecimal(100));

        this.paymentService.save(payment);

        list = this.paymentService.getAll();
        Assert.assertTrue(list.size()==(listSize + 1));
    }

    @Test
    @Transactional
    public void save() throws Exception {
        Collection<Payment> list = this.paymentService.getAll();

        Payment payment = new Payment();
        payment.setRatePayment(new BigDecimal(200));
        payment.setAmountPayment(new BigDecimal(300));
        payment.setBodyCredit(new BigDecimal(100));

        this.paymentService.save(payment);

        Collection<Payment> listN = this.paymentService.getAll();
        Assert.assertTrue(listN.size()==(list.size() + 1));
    }

    @Test
    @Transactional
    public void removeById() throws Exception {


        this.paymentService.remove(3l);

        Payment paymentN = paymentService.getById(3l);

        Assert.assertTrue(paymentN == null);
    }

    @Test
    @Transactional
    public void removeObject() throws Exception {

        Payment payment = new Payment();
        payment.setRatePayment(new BigDecimal(200));
        payment.setAmountPayment(new BigDecimal(300));
        payment.setBodyCredit(new BigDecimal(100));

        paymentService.save(payment);

        Collection<Payment> listOne = this.paymentService.getAll();

        paymentService.remove(payment);

        Collection<Payment> listTwo = this.paymentService.getAll();

        Assert.assertFalse(listTwo.size() == listOne.size());

    }


}