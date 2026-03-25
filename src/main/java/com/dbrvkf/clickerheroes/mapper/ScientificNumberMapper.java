package com.dbrvkf.clickerheroes.mapper;

import com.dbrvkf.clickerheroes.dto.ScientificNumberDto;
import com.dbrvkf.clickerheroes.entity.common.ScientificNumber;
import com.dbrvkf.clickerheroes.mapper.config.GlobalMapperConfig;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(config = GlobalMapperConfig.class)
public interface ScientificNumberMapper {

  ScientificNumber toEntity(ScientificNumberDto scientificNumberDto);

  ScientificNumberDto toDto(ScientificNumber scientificNumber);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  ScientificNumber partialUpdate(
      ScientificNumberDto scientificNumberDto, @MappingTarget ScientificNumber scientificNumber);
}
