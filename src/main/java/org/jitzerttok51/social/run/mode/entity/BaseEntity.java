package org.jitzerttok51.social.run.mode.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZoneId;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "modified_date", nullable = false)
    private LocalDateTime modifiedDate;

    @PrePersist
    public void onPersist() {
        this.createdDate = LocalDateTime.now(ZoneId.of("UTC"));
        this.modifiedDate = this.createdDate;
    }

    @PreUpdate
    public void onUpdate() {
        this.modifiedDate = LocalDateTime.now(ZoneId.of("UTC"));
    }
}
