package com.dbrvkf.clickerheroes.dto;

import com.dbrvkf.clickerheroes.entity.Hero;
import java.io.Serializable;

/** DTO for {@link Hero} */
public record HeroDto(
    String name, String details, ScientificNumberDto baseDps, ScientificNumberDto basePrice)
    implements Serializable {}
