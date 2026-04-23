package com.metDaisy.clickerheroes.entity;

import com.metDaisy.clickerheroes.entity.base.ImmutableEntity;
import com.metDaisy.clickerheroes.entity.common.ScientificNumber;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.LinkedHashSet;
import java.util.Set;
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

  @Embedded
  @AttributeOverrides({
    @AttributeOverride(name = "mantissa", column = @Column(name = "base_dps_mantissa")),
    @AttributeOverride(name = "exponent", column = @Column(name = "base_dps_exponent"))
  })
  private ScientificNumber baseDps;

  @Embedded
  @AttributeOverrides({
    @AttributeOverride(name = "mantissa", column = @Column(name = "base_price_mantissa")),
    @AttributeOverride(name = "exponent", column = @Column(name = "base_price_exponent"))
  })
  private ScientificNumber basePrice;

  @OneToMany(mappedBy = "hero")
  private Set<HeroSkill> heroSkills = new LinkedHashSet<>();

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
    this.baseDps = new ScientificNumber(baseDpsMantissa, baseDpsExponent);
    this.basePrice = new ScientificNumber(basePriceMantissa, basePriceExponent);
  }
}
