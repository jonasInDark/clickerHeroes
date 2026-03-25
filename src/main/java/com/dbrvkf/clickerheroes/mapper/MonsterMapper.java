package com.dbrvkf.clickerheroes.mapper;

import com.dbrvkf.clickerheroes.dto.MonsterDto;
import com.dbrvkf.clickerheroes.entity.Monster;
import com.dbrvkf.clickerheroes.mapper.config.GlobalMapperConfig;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
    config = GlobalMapperConfig.class,
    uses = {UserMapper.class, ScientificNumberMapper.class})
public interface MonsterMapper {

  Monster toEntity(MonsterDto monsterDto);

  MonsterDto toDto(Monster monster);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  Monster partialUpdate(MonsterDto monsterDto, @MappingTarget Monster monster);
}
