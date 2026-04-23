package com.metDaisy.clickerheroes.mapper;

import com.metDaisy.clickerheroes.dto.HiredHeroDto;
import com.metDaisy.clickerheroes.entity.HiredHero;
import com.metDaisy.clickerheroes.mapper.config.GlobalMapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(
    config = GlobalMapperConfig.class,
    uses = {UserMapper.class, HeroMapper.class, ScientificNumberMapper.class})
public interface HiredHeroMapper extends BaseMapper<HiredHero, HiredHeroDto> {

  HiredHero toEntity(HiredHeroDto hiredHeroDto);

  HiredHero partialUpdate(HiredHeroDto hiredHeroDto, @MappingTarget HiredHero hiredHero);
}
