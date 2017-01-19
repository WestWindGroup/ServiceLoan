package com.serviceloan.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * Simple JavaBean domain object that represents a CreditType.
 *
 * @author Eugene Artemenko
 */

@Entity
@Table(name = "types")
public class CreditType extends BaseEntity {

    @Column(name = "type")
    private String type;

    @OneToMany(mappedBy = "creditType")
    private Set<Credit> credits;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

        CreditType that = (CreditType) o;

        return type != null ? type.equals(that.type) : that.type == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

}
