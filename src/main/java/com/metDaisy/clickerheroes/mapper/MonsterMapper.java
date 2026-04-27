package com.metDaisy.clickerheroes.mapper;

import com.metDaisy.clickerheroes.dto.MonsterDto;
import com.metDaisy.clickerheroes.entity.Monster;
import com.metDaisy.clickerheroes.mapper.base.BaseMapper;
import com.metDaisy.clickerheroes.mapper.config.GlobalMapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(
    config = GlobalMapperConfig.class,
    uses = {UserMapper.class, ScientificNumberMapper.class})
public interface MonsterMapper extends BaseMapper<Monster, MonsterDto> {

  Monster partialUpdate(MonsterDto monsterDto, @MappingTarget Monster monster);
}
