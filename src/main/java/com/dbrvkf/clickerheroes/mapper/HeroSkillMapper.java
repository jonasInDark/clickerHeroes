package com.dbrvkf.clickerheroes.mapper;

import com.dbrvkf.clickerheroes.dto.HeroSkillDto;
import com.dbrvkf.clickerheroes.entity.HeroSkill;
import com.dbrvkf.clickerheroes.mapper.config.GlobalMapperConfig;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
    config = GlobalMapperConfig.class,
    uses = {HeroMapper.class, ScientificNumberMapper.class})
public interface HeroSkillMapper {

  HeroSkill toEntity(HeroSkillDto heroSkillDto);

  HeroSkillDto toDto(HeroSkill heroSkill);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  HeroSkill partialUpdate(HeroSkillDto heroSkillDto, @MappingTarget HeroSkill heroSkill);
}
