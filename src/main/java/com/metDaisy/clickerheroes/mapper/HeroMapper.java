package com.metDaisy.clickerheroes.mapper;

import com.metDaisy.clickerheroes.dto.HeroDto;
import com.metDaisy.clickerheroes.entity.Hero;
import com.metDaisy.clickerheroes.mapper.base.BaseMapper;
import com.metDaisy.clickerheroes.mapper.config.GlobalMapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(
    config = GlobalMapperConfig.class,
    uses = {ScientificNumberMapper.class})
public interface HeroMapper extends BaseMapper<Hero, HeroDto> {

  Hero partialUpdate(HeroDto heroDto, @MappingTarget Hero hero);
}
