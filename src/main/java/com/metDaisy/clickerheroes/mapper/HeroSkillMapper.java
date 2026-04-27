package com.metDaisy.clickerheroes.mapper;

import com.metDaisy.clickerheroes.dto.HeroSkillDto;
import com.metDaisy.clickerheroes.entity.HeroSkill;
import com.metDaisy.clickerheroes.mapper.base.BaseMapper;
import com.metDaisy.clickerheroes.mapper.config.GlobalMapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(
    config = GlobalMapperConfig.class,
    uses = {HeroMapper.class, ScientificNumberMapper.class})
public interface HeroSkillMapper extends BaseMapper<HeroSkill, HeroSkillDto> {

  HeroSkill partialUpdate(HeroSkillDto heroSkillDto, @MappingTarget HeroSkill heroSkill);
}
