package com.serviceloan.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
