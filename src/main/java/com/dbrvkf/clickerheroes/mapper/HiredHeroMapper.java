package com.dbrvkf.clickerheroes.mapper;

import com.dbrvkf.clickerheroes.dto.HiredHeroDto;
import com.dbrvkf.clickerheroes.entity.HiredHero;
import com.dbrvkf.clickerheroes.mapper.config.GlobalMapperConfig;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
    config = GlobalMapperConfig.class,
    uses = {UserMapper.class, HeroMapper.class, ScientificNumberMapper.class})
public interface HiredHeroMapper {

  HiredHero toEntity(HiredHeroDto hiredHeroDto);

  HiredHeroDto toDto(HiredHero hiredHero);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  HiredHero partialUpdate(HiredHeroDto hiredHeroDto, @MappingTarget HiredHero hiredHero);
}
