package com.dbrvkf.clickerheroes.entity;

import com.dbrvkf.clickerheroes.entity.base.ImmutableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.*;
import org.hibernate.annotations.Immutable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Immutable
@Entity
@Table(name = "heroes")
public class Hero extends ImmutableEntity {

  @Column(name = "name", nullable = false, length = 20)
  private String name;

  @Column(name = "details", nullable = false)
  private String details;

  @Column(name = "base_dps_mantissa", nullable = false)
  private Double baseDpsMantissa;

  @Column(name = "base_dps_exponent", nullable = false)
  private Integer baseDpsExponent;

  @Column(name = "base_price_mantissa", nullable = false)
  private Double basePriceMantissa;

  @Column(name = "base_price_exponent", nullable = false)
  private Integer basePriceExponent;

  @OneToMany(mappedBy = "hero")
  private List<HeroSkill> heroSkills = new ArrayList<>();

  @Builder
  public Hero(
      String name,
      String details,
      Double baseDpsMantissa,
      Integer baseDpsExponent,
      Double basePriceMantissa,
      Integer basePriceExponent) {
    this.name = name;
    this.details = details;
    this.baseDpsMantissa = baseDpsMantissa;
    this.baseDpsExponent = baseDpsExponent;
    this.basePriceMantissa = basePriceMantissa;
    this.basePriceExponent = basePriceExponent;
  }
}
