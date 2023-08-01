package tech.obss.entity;

import jakarta.persistence.*;

import java.util.Date;

@MappedSuperclass
public class EntityBase {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @Column(name = "UPDATED_DATE")
    private Date updatedDate;

    @Column(name = "ACTIVE")
    private Boolean active;

    @Column(name = "OPERATION_TYPE")
    private String operation;

    @PrePersist
    public void onPrePersist() {
        this.createdDate = new Date();
        this.updatedDate = new Date();
        this.active = true;
        this.operation = "SAVE";
    }

    @PreUpdate
    public void onPreUpdate() {
        this.updatedDate = new Date();
        this.operation = "UPDATE";
    }

    @PreRemove
    public void onPreRemove() {
        this.updatedDate = new Date();
        this.active = false;
        this.operation = "DELETE";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
