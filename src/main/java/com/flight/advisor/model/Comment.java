package com.flight.advisor.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment extends BaseEntity {

    private String text;

    private ZonedDateTime createdDate;

    private ZonedDateTime modifiedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private City city;

    @PrePersist
    private void prePersist() {
        createdDate = ZonedDateTime.now();
    }

    @PreUpdate
    private void preUpdate() {
        modifiedDate = ZonedDateTime.now();
    }
}
