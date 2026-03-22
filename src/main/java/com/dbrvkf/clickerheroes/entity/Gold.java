package com.dbrvkf.clickerheroes.entity;

import com.dbrvkf.clickerheroes.entity.base.MutableEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "gold")
public class Gold extends MutableEntity {

  @OneToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Column(name = "gold_mantissa", nullable = false)
  private Double goldMantissa;

  @Column(name = "gold_exponent", nullable = false)
  private Integer goldExponent;
}
