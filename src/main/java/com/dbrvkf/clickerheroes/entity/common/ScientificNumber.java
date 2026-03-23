package com.dbrvkf.clickerheroes.entity.common;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ScientificNumber {
  private static double[] POWER_OF_TEN = {
    1.0,
    10.0,
    100.0,
    1000.0,
    10000.0,
    100000.0,
    1000000.0,
    10000000.0,
    100000000.0,
    1000000000.0,
    10000000000.0
  };
  private double mantissa;
  private int exponent;

  public void multiply(int value) {
    mantissa *= value;
    normalize();
  }

  public void multiply(ScientificNumber other) {
    mantissa *= other.mantissa;
    exponent += other.exponent;
    normalize();
  }

  // todo
  public void subtract(ScientificNumber other) {

  }

  private void normalize() {
    if ((long) mantissa == 0) {
      return;
    }
    double logged = Math.log10(mantissa);
    int exp = (int) Math.floor(logged);
    mantissa /= POWER_OF_TEN[exp];
    exponent += exp;
    truncate();
  }

  private void truncate() {
    mantissa = Math.floor(mantissa * 1000.0) / 1000.0;
  }
}
