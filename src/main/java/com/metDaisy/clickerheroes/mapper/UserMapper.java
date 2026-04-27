package com.metDaisy.clickerheroes.mapper;

import com.metDaisy.clickerheroes.dto.UserDto;
import com.metDaisy.clickerheroes.dto.request.UserCreateRequest;
import com.metDaisy.clickerheroes.entity.User;
import com.metDaisy.clickerheroes.mapper.base.BaseMapper;
import com.metDaisy.clickerheroes.mapper.config.GlobalMapperConfig;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(
    config = GlobalMapperConfig.class,
    uses = {HiredHeroMapper.class})
public interface UserMapper extends BaseMapper<User, UserDto> {

  User toEntity(UserCreateRequest request);

  User toEntity(UserDto userDto);

  User partialUpdate(UserDto userDto, @MappingTarget User user);

  @AfterMapping
  default void linkHiredHeroes(@MappingTarget User user) {
    user.getHiredHeroes().forEach(hiredHero -> hiredHero.setUser(user));
  }
}
