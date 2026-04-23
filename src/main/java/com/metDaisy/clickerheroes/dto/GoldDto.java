package com.metDaisy.clickerheroes.dto;

import com.metDaisy.clickerheroes.entity.Gold;
import java.io.Serializable;

/** DTO for {@link Gold} */
public record GoldDto(UserDto user, ScientificNumberDto gold) implements Serializable {}
