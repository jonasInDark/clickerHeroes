package com.metDaisy.clickerheroes.entity;

import com.metDaisy.clickerheroes.entity.base.MutableEntity;
import com.metDaisy.clickerheroes.entity.common.ScientificNumber;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "hired_heroes")
public class HiredHero extends MutableEntity {

  @Setter
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "hero_id", nullable = false)
  private Hero hero;

  @Embedded
  @AttributeOverrides({
      @AttributeOverride(name = "mantissa", column = @Column(name = "dps_mantissa")),
      @AttributeOverride(name = "exponent", column = @Column(name = "dps_exponent"))
  })
  private ScientificNumber dps;

  @Embedded
  @AttributeOverrides({
      @AttributeOverride(name = "mantissa", column = @Column(name = "price_mantissa")),
      @AttributeOverride(name = "exponent", column = @Column(name = "price_exponent"))
  })
  private ScientificNumber price;

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
    this.dps = new ScientificNumber(dpsMantissa, dpsExponent);
    this.price = new ScientificNumber(priceMantissa, priceExponent);
    this.level = level;
  }
}
