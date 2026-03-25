package com.dbrvkf.clickerheroes.mapper;

import com.dbrvkf.clickerheroes.dto.HeroDto;
import com.dbrvkf.clickerheroes.entity.Hero;
import com.dbrvkf.clickerheroes.mapper.config.GlobalMapperConfig;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
    config = GlobalMapperConfig.class,
    uses = {ScientificNumberMapper.class})
public interface HeroMapper {

  Hero toEntity(HeroDto heroDto);

  HeroDto toDto(Hero hero);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  Hero partialUpdate(HeroDto heroDto, @MappingTarget Hero hero);
}
