package com.dbrvkf.clickerheroes.dto;

import com.dbrvkf.clickerheroes.entity.common.ScientificNumber;
import java.io.Serializable;

/** DTO for {@link ScientificNumber} */
public record ScientificNumberDto(double mantissa, int exponent) implements Serializable {}
