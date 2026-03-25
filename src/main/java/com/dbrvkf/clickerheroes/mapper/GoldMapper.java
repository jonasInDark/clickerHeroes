package com.dbrvkf.clickerheroes.mapper;

import com.dbrvkf.clickerheroes.dto.GoldDto;
import com.dbrvkf.clickerheroes.entity.Gold;
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
public interface GoldMapper {

  Gold toEntity(GoldDto goldDto);

  GoldDto toDto(Gold gold);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  Gold partialUpdate(GoldDto goldDto, @MappingTarget Gold gold);
}
