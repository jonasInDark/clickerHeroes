package com.dbrvkf.clickerheroes.entity.base;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import java.time.Instant;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;

@Getter
@Setter
@MappedSuperclass
public abstract class MutableEntity extends ImmutableEntity {
  @LastModifiedDate
  @Column(name = "updated_at")
  private Instant updatedAt;
}
