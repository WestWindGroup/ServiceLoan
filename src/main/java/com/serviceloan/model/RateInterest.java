package com.serviceloan.model;


import javax.persistence.*;
import java.util.Set;

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

    @OneToMany(mappedBy = "percent")
    private Set<Credit> credits;

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public Set<Credit> getCredits() {
        return credits;
    }

    public void setCredits(Set<Credit> credits) {
        this.credits = credits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        RateInterest interest = (RateInterest) o;

        return Double.compare(interest.rate, rate) == 0;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(rate);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

}
