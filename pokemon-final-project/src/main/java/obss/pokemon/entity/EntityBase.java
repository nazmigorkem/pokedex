package obss.pokemon.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class EntityBase {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @CreatedBy
    private String createdBy;

    @LastModifiedBy
    private String updatedBy;

    @CreatedDate
    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @LastModifiedDate
    @Column(name = "UPDATED_DATE")
    private Date updatedDate;

    @Column(name = "ACTIVE")
    private Boolean active;

    @Column(name = "OPERATION_TYPE")
    private String operation;

    @PrePersist
    public void onPrePersist() {
        this.active = true;
        this.operation = "SAVE";
    }

    @PreUpdate
    public void onPreUpdate() {
        this.operation = "UPDATE";
    }

    @PreRemove
    public void onPreRemove() {
        this.updatedDate = new Date();
        this.active = false;
        this.operation = "DELETE";
    }
}
