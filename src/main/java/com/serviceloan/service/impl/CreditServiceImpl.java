package com.serviceloan.service.impl;

import com.serviceloan.dao.CreditDAO;
import com.serviceloan.logic.CreditLogicAnnuityImpl;
import com.serviceloan.logic.CreditLogicBalanceDecreaseImpl;
import com.serviceloan.model.Credit;
import com.serviceloan.model.Payment;
import com.serviceloan.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

@Service
public class CreditServiceImpl implements CreditService{

    @Autowired
    private CreditDAO creditDAO;

    @Autowired
    private CreditLogicAnnuityImpl creditLogicAnnuity;

    @Autowired
    private CreditLogicBalanceDecreaseImpl creditLogicBalanceDecrease;

    @Override
    @Transactional
    public void save(Credit credit) {
        creditDAO.save(credit);
    }

    @Override
    @Transactional
    public Credit getById(long id) {
        return creditDAO.getById(id);
    }

    @Override
    @Transactional
    public Collection<Credit> getAll() {
        return creditDAO.getAll();
    }

    @Override
    @Transactional
    public void remove(Credit credit) {
        creditDAO.remove(credit);
    }

    @Override
    @Transactional
    public void remove(long id) {
        creditDAO.remove(id);
    }

    @Override
    public BigDecimal minPayment(Credit credit) {
        if (credit.getCreditType().getType().equals("Differentiated")){
            return creditLogicBalanceDecrease.minPayment(credit);
        }else{
            return creditLogicAnnuity.minPayment(credit);
        }
    }

    @Override
    public List<Payment> listPayments(Credit credit) {
        if (credit.getCreditType().getType().equals("Differentiated")){
            return creditLogicBalanceDecrease.listPayments(credit);
        }else{
            return creditLogicAnnuity.listPayments(credit);
        }
    }

    @Override
    public BigDecimal rateInPayment(Credit credit) {
        if (credit.getCreditType().getType().equals("Differentiated")){
            return creditLogicBalanceDecrease.rateInPayment(credit);
        }else{
            return creditLogicAnnuity.rateInPayment(credit);
        }
    }

    @Override
    public BigDecimal bodyInPayment(Credit credit,BigDecimal rate, BigDecimal payment) {
        if (credit.getCreditType().getType().equals("Differentiated")){
            return creditLogicBalanceDecrease.bodyInPayment(rate, payment);
        }else{
            return creditLogicAnnuity.bodyInPayment(rate, payment);
        }
    }

    @Override
    public BigDecimal maxPayment(Credit credit){
        if (credit.getCreditType().getType().equals("Differentiated")){
            return creditLogicBalanceDecrease.maxPayment(credit);
        }else{
            return creditLogicAnnuity.maxPayment(credit);
        }
    }
}
