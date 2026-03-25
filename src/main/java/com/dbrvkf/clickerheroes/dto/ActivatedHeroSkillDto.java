package com.dbrvkf.clickerheroes.dto;

import com.dbrvkf.clickerheroes.entity.ActivatedHeroSkill;
import com.dbrvkf.clickerheroes.entity.constant.HeroSkillStatus;
import java.io.Serializable;

/** DTO for {@link ActivatedHeroSkill} */
public record ActivatedHeroSkillDto(
    HiredHeroDto hiredHero, HeroSkillDto heroSkill, HeroSkillStatus status)
    implements Serializable {}
