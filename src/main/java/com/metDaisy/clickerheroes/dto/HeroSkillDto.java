package com.metDaisy.clickerheroes.dto;

import com.metDaisy.clickerheroes.entity.HeroSkill;
import com.metDaisy.clickerheroes.entity.constant.HeroSkillType;
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
