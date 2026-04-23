package com.metDaisy.clickerheroes.entity;

import com.metDaisy.clickerheroes.entity.base.ImmutableEntity;
import com.metDaisy.clickerheroes.entity.common.ScientificNumber;
import com.metDaisy.clickerheroes.entity.constant.HeroSkillType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Immutable
@Entity
@Table(name = "hero_skills")
public class HeroSkill extends ImmutableEntity {

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "hero_id", nullable = false)
  private Hero hero;

  @Column(name = "name", nullable = false, length = 50)
  private String name;

  @Column(name = "details", nullable = false)
  private String details;

  @Column(name = "required_level", nullable = false)
  private Integer requiredLevel;

  @Embedded
  private ScientificNumber requiredGold;

  @Enumerated(EnumType.STRING)
  @Column(name = "skill_type", nullable = false, length = 20)
  private HeroSkillType skillType;

  @Builder
  public HeroSkill(
      Hero hero,
      String name,
      String details,
      Integer requiredLevel,
      Double requiredGoldMantissa,
      Integer requiredGoldExponent,
      HeroSkillType skillType) {
    this.hero = hero;
    this.name = name;
    this.details = details;
    this.requiredLevel = requiredLevel;
    this.requiredGold = new ScientificNumber(requiredGoldMantissa, requiredGoldExponent);
    this.skillType = skillType;
  }
}
