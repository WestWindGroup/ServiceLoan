package com.serviceloan.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Simple JavaBean domain object that represents a RateInterest.
 *
 * @author Eugene Artemenko
 */

@Entity
@Table(name = "rate_interest")
public class RateInterest extends BaseEntity{

    @Column(name = "rate")
    private double rate;

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
