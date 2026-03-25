package com.dbrvkf.clickerheroes.mapper;

import com.dbrvkf.clickerheroes.dto.ActivatedHeroSkillDto;
import com.dbrvkf.clickerheroes.entity.ActivatedHeroSkill;
import com.dbrvkf.clickerheroes.mapper.config.GlobalMapperConfig;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
    config = GlobalMapperConfig.class,
    uses = {HiredHeroMapper.class, HeroSkillMapper.class})
public interface ActivatedHeroSkillMapper {

  ActivatedHeroSkill toEntity(ActivatedHeroSkillDto activatedHeroSkillDto);

  ActivatedHeroSkillDto toDto(ActivatedHeroSkill activatedHeroSkill);
}
