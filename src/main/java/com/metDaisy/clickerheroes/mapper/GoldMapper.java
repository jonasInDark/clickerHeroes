package com.metDaisy.clickerheroes.mapper;

import com.metDaisy.clickerheroes.dto.GoldDto;
import com.metDaisy.clickerheroes.entity.Gold;
import com.metDaisy.clickerheroes.mapper.config.GlobalMapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(
    config = GlobalMapperConfig.class,
    uses = {UserMapper.class, ScientificNumberMapper.class})
public interface GoldMapper extends BaseMapper<Gold, GoldDto> {

  Gold toEntity(GoldDto goldDto);

  Gold partialUpdate(GoldDto goldDto, @MappingTarget Gold gold);
}
