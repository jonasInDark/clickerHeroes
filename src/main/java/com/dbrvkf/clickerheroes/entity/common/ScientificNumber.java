package com.dbrvkf.clickerheroes.entity.common;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ScientificNumber {
  private Double mantissa;
  private int exponent;

  // todo
  public void multiply(int value) {}
}
