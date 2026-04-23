package com.metDaisy.clickerheroes.mapper;

import com.metDaisy.clickerheroes.dto.ScientificNumberDto;
import com.metDaisy.clickerheroes.entity.common.ScientificNumber;
import com.metDaisy.clickerheroes.mapper.config.GlobalMapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = GlobalMapperConfig.class)
public interface ScientificNumberMapper extends BaseMapper<ScientificNumber, ScientificNumberDto> {

  ScientificNumber toEntity(ScientificNumberDto scientificNumberDto);

  ScientificNumber partialUpdate(
      ScientificNumberDto scientificNumberDto, @MappingTarget ScientificNumber scientificNumber);
}
