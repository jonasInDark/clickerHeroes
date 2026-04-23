package com.metDaisy.clickerheroes.mapper;

import com.metDaisy.clickerheroes.dto.ActivatedHeroSkillDto;
import com.metDaisy.clickerheroes.entity.ActivatedHeroSkill;
import com.metDaisy.clickerheroes.mapper.config.GlobalMapperConfig;
import org.mapstruct.Mapper;

@Mapper(
    config = GlobalMapperConfig.class,
    uses = {HiredHeroMapper.class, HeroSkillMapper.class})
public interface ActivatedHeroSkillMapper
    extends BaseMapper<ActivatedHeroSkill, ActivatedHeroSkillDto> {

  ActivatedHeroSkill toEntity(ActivatedHeroSkillDto activatedHeroSkillDto);
}
