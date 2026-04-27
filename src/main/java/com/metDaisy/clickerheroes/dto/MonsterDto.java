package com.metDaisy.clickerheroes.dto;

import com.metDaisy.clickerheroes.entity.Monster;
import java.io.Serializable;

/**
 * DTO for {@link Monster}
 */
public record MonsterDto(UserDto user, ScientificNumberDto hp) implements Serializable {

}
