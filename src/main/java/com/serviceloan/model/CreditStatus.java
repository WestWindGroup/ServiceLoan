package com.serviceloan.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
