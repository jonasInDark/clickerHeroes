package com.dbrvkf.clickerheroes.dto;

import com.dbrvkf.clickerheroes.entity.HeroSkill;
import com.dbrvkf.clickerheroes.entity.constant.HeroSkillType;
import java.io.Serializable;

/** DTO for {@link HeroSkill} */
public record HeroSkillDto(
    HeroDto hero,
    String name,
    String details,
    Integer requiredLevel,
    ScientificNumberDto requiredGold,
    HeroSkillType skillType)
    implements Serializable {}
