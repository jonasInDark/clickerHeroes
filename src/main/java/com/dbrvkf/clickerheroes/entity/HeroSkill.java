package com.dbrvkf.clickerheroes.entity;

import com.dbrvkf.clickerheroes.entity.base.BaseEntity;
import com.dbrvkf.clickerheroes.entity.constant.HeroSkillType;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.Immutable;

@Getter
@Entity
@Table(name = "hero_skills")
@Immutable
public class HeroSkill extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "heroes_id", nullable = false)
    private Hero heroes;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "details", nullable = false)
    private String details;

    @Column(name = "skill_type", nullable = false, length = 10)
    private HeroSkillType skillType;

}
