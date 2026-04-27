package com.metDaisy.clickerheroes.dto;

import com.metDaisy.clickerheroes.entity.ActivatedHeroSkill;
import com.metDaisy.clickerheroes.entity.constant.HeroSkillStatus;
import java.io.Serializable;

/**
 * DTO for {@link ActivatedHeroSkill}
 */
public record ActivatedHeroSkillDto(
    HiredHeroDto hiredHero, HeroSkillDto heroSkill, HeroSkillStatus status)
    implements Serializable {

}
