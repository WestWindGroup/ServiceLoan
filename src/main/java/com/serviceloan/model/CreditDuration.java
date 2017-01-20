package com.serviceloan.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * Simple JavaBean domain object that represents a CreditDuration.
 *
 * @author Eugene Artemenko
 */

@Entity
@Table(name = "duration")
public class CreditDuration extends BaseEntity{

    @Column(name = "duration")
    private int duration;

    @OneToMany(mappedBy = "duration")
    private Set<Credit> credits;

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Set<Credit> getCredits() {
        return credits;
    }

    public void setCredits(Set<Credit> credits) {
        this.credits = credits;
    }
}
