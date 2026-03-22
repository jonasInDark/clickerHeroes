package com.dbrvkf.clickerheroes.entity;

import com.dbrvkf.clickerheroes.entity.base.UpdatableEntity;
import com.dbrvkf.clickerheroes.entity.constant.HeroSkillStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "activated_hero_skills")
public class ActivatedHeroSkill extends UpdatableEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "hired_hero_id", nullable = false)
    private HiredHero hiredHero;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "hero_skill_id", nullable = false)
    private HeroSkill heroSkill;

    @Column(name = "status", nullable = false, length = 10)
    private HeroSkillStatus status = HeroSkillStatus.NOT_LEARNED;

}
