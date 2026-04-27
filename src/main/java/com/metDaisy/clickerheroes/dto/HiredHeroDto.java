package com.metDaisy.clickerheroes.dto;

import com.metDaisy.clickerheroes.entity.HiredHero;
import java.io.Serializable;

/**
 * DTO for {@link HiredHero}
 */
public record HiredHeroDto(
    UserDto user, HeroDto hero, ScientificNumberDto dps, ScientificNumberDto price, Integer level)
    implements Serializable {

}
