package com.serviceloan.model;



import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "payment")
public class Payment extends BaseEntity{

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "date_payment")
    private Date paymentDate;

    @Column(name = "amount_payment")
    private BigDecimal amountPayment;

    @Column(name = "body_credit")
    private BigDecimal bodyCredit;

    @Column(name = "rate_payment")
    private BigDecimal ratePayment;

    @ManyToOne
    @JoinTable(name = "credit_payment", joinColumns = {@JoinColumn(name = "payment_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "credit_id", referencedColumnName = "id")})
    private Credit credit;

    @PrePersist
    public void getDate() {
        paymentDate = new Date();
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public BigDecimal getAmountPayment() {
        return amountPayment;
    }

    public void setAmountPayment(BigDecimal amountPayment) {
        this.amountPayment = amountPayment;
    }

    public BigDecimal getBodyCredit() {
        return bodyCredit;
    }

    public void setBodyCredit(BigDecimal bodyCredit) {
        this.bodyCredit = bodyCredit;
    }

    public BigDecimal getRatePayment() {
        return ratePayment;
    }

    public void setRatePayment(BigDecimal ratePayment) {
        this.ratePayment = ratePayment;
    }

    public Credit getCredit() {
        return credit;
    }

    public void setCredit(Credit credit) {
        this.credit = credit;
    }
}
