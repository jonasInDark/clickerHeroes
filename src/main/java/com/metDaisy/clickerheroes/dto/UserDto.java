package com.metDaisy.clickerheroes.dto;

import com.metDaisy.clickerheroes.entity.User;
import java.io.Serializable;
import java.util.Set;

/** DTO for {@link User} */
public record UserDto(String name, String password, Integer stage, Set<HiredHeroDto> hiredHeroes)
    implements Serializable {}
