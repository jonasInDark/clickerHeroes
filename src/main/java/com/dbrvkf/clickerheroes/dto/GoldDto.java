package com.dbrvkf.clickerheroes.dto;

import com.dbrvkf.clickerheroes.entity.Gold;
import java.io.Serializable;

/** DTO for {@link Gold} */
public record GoldDto(UserDto user, ScientificNumberDto gold) implements Serializable {}
