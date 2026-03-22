package com.dbrvkf.clickerheroes.entity.base;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;

@Getter
@Setter
@MappedSuperclass
public abstract class UpdatableEntity extends BaseEntity {
    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDate updatedAt;

}
