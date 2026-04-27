package com.metDaisy.clickerheroes.entity;

import com.metDaisy.clickerheroes.entity.base.MutableEntity;
import com.metDaisy.clickerheroes.entity.constant.HeroSkillStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "activated_hero_skills")
public class ActivatedHeroSkill extends MutableEntity {

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "hired_hero_id", nullable = false)
  private HiredHero hiredHero;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "hero_skill_id", nullable = false)
  private HeroSkill heroSkill;

  @Builder.Default
  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false, length = 10)
  private HeroSkillStatus status = HeroSkillStatus.NOT_LEARNED;

  public ActivatedHeroSkill(HiredHero hiredHero, HeroSkill heroSkill, HeroSkillStatus status) {
    this.hiredHero = hiredHero;
    this.heroSkill = heroSkill;
    this.status = status;
  }
}
