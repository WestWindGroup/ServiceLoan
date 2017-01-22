package com.serviceloan.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

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

    @OneToMany(mappedBy = "credit")
    private Set<Payment> payments;

    @ManyToOne
    @JoinTable(name = "credit_rate", joinColumns = {@JoinColumn(name = "credit_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "rate_id", referencedColumnName = "id")})
    private RateInterest percent;

    @ManyToOne
    @JoinTable(name = "credit_duration", joinColumns = {@JoinColumn(name = "credit_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "duration_id", referencedColumnName = "id")})
    private CreditDuration duration;

    @ManyToOne
    @JoinTable(name = "credit_type", joinColumns = {@JoinColumn(name = "credit_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "type_id", referencedColumnName = "id")})
    private CreditType creditType;

    @ManyToOne
    @JoinTable(name = "credit_status", joinColumns = {@JoinColumn(name = "credit_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "status_id", referencedColumnName = "id")})
    private CreditStatus creditStatus;

    @ManyToOne
    @JoinTable(name = "client_credits", joinColumns = {@JoinColumn(name = "credit_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "client_id", referencedColumnName = "id")})
    private Client client;

    @PrePersist
    public void getDate() {
        openDate = new Date();
    }

    public Set<Payment> getPayments() {
        return payments;
    }

    public void setPayments(Set<Payment> payments) {
        this.payments = payments;
    }

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

    public CreditDuration getDuration() {
        return duration;
    }

    public void setDuration(CreditDuration duration) {
        this.duration = duration;
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
