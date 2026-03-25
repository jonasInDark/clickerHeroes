package com.dbrvkf.clickerheroes.dto;

import com.dbrvkf.clickerheroes.entity.User;
import java.io.Serializable;

/** DTO for {@link User} */
public record UserDto(String name, String password, Integer stage) implements Serializable {}
