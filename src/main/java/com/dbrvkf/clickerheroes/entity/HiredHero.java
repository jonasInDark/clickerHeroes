package com.dbrvkf.clickerheroes.entity;

import com.dbrvkf.clickerheroes.entity.base.MutableEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "hired_heroes")
public class HiredHero extends MutableEntity {

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "hero_id", nullable = false)
  private Hero hero;

  @Column(name = "dps_mantissa", nullable = false)
  private Double dpsMantissa;

  @Column(name = "dps_exponent", nullable = false)
  private Integer dpsExponent;

  @Column(name = "price_mantissa", nullable = false)
  private Double priceMantissa;

  @Column(name = "price_exponent", nullable = false)
  private Integer priceExponent;

  @Column(name = "level", nullable = false)
  private Integer level;

  @Builder
  public HiredHero(
      User user,
      Hero hero,
      Double dpsMantissa,
      Integer dpsExponent,
      Double priceMantissa,
      Integer priceExponent,
      Integer level) {
    this.user = user;
    this.hero = hero;
    this.dpsMantissa = dpsMantissa;
    this.dpsExponent = dpsExponent;
    this.priceMantissa = priceMantissa;
    this.priceExponent = priceExponent;
    this.level = level;
  }
}
