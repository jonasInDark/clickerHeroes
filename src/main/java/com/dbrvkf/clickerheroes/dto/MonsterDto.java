package com.dbrvkf.clickerheroes.dto;

import com.dbrvkf.clickerheroes.entity.Monster;
import java.io.Serializable;

/** DTO for {@link Monster} */
public record MonsterDto(UserDto user, ScientificNumberDto hp) implements Serializable {}
