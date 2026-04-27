package com.metDaisy.clickerheroes.dto;

import com.metDaisy.clickerheroes.entity.Hero;
import java.io.Serializable;

/**
 * DTO for {@link Hero}
 */
public record HeroDto(
    String name, String details, ScientificNumberDto baseDps, ScientificNumberDto basePrice)
    implements Serializable {

}
