package com.serviceloan.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Simple JavaBean domain object that represents a Credit.
 *
 * @author Eugene Artemenko
 */


@Entity
@Table(name = "credits")
public class Credit extends BaseEntity{

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "debt")
    private BigDecimal debt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "open_date")
    private Date openDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "shut_date")
    private Date shutDate;

    @OneToOne
    @JoinTable(name = "credit_interest", joinColumns = {@JoinColumn(name = "credit_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "percent_id", referencedColumnName = "id")})
    private RateInterest percent;

    @OneToOne
    @JoinTable(name = "credit_type", joinColumns = {@JoinColumn(name = "credit_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "type_id", referencedColumnName = "id")})
    private CreditType creditType;

    @OneToOne
    @JoinTable(name = "credit_status", joinColumns = {@JoinColumn(name = "credit_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "status_id", referencedColumnName = "id")})
    private CreditStatus creditStatus;


    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getDebt() {
        return debt;
    }

    public void setDebt(BigDecimal debt) {
        this.debt = debt;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public Date getShutDate() {
        return shutDate;
    }

    public void setShutDate(Date shutDate) {
        this.shutDate = shutDate;
    }

    public RateInterest getPercent() {
        return percent;
    }

    public void setPercent(RateInterest percent) {
        this.percent = percent;
    }

    public CreditType getCreditType() {
        return creditType;
    }

    public void setCreditType(CreditType creditType) {
        this.creditType = creditType;
    }

    public CreditStatus getCreditStatus() {
        return creditStatus;
    }

    public void setCreditStatus(CreditStatus creditStatus) {
        this.creditStatus = creditStatus;
    }
}
