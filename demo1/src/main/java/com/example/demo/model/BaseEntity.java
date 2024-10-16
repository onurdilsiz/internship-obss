package com.example.demo.model;

import com.example.demo.utils.SecurityUtils;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
public class BaseEntity implements Serializable {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String createdBy;

    private String updatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @PrePersist
    public void onPrePersist() {
        setCreatedAt(LocalDateTime.now());
        setUpdatedAt(LocalDateTime.now());
        final String currrentUser = SecurityUtils.getCurrentUser();
        setCreatedBy(currrentUser);
        setUpdatedBy(currrentUser);
    }
@PreUpdate
    public void onPreUpdate() {

        setUpdatedAt(LocalDateTime.now());
        final String currrentUser = SecurityUtils.getCurrentUser();
        setUpdatedBy(currrentUser);

    }


}
