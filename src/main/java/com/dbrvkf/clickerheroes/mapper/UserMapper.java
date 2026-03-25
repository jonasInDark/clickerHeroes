package com.dbrvkf.clickerheroes.mapper;

import com.dbrvkf.clickerheroes.dto.UserDto;
import com.dbrvkf.clickerheroes.entity.User;
import com.dbrvkf.clickerheroes.mapper.config.GlobalMapperConfig;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(config = GlobalMapperConfig.class)
public interface UserMapper {

  User toEntity(UserDto userDto);

  UserDto toDto(User user);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  User partialUpdate(UserDto userDto, @MappingTarget User user);
}
