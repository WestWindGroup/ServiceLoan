package com.serviceloan.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * Simple JavaBean domain object that represents a CreditStatus.
 *
 * @author Eugene Artemenko
 */

@Entity
@Table(name = "statuses")
public class CreditStatus extends BaseEntity{

    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "creditStatus")
    private Set<Credit> credits;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

        CreditStatus that = (CreditStatus) o;

        return status != null ? status.equals(that.status) : that.status == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
