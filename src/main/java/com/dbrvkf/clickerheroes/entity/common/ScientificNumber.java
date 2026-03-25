package com.dbrvkf.clickerheroes.entity.common;

import jakarta.persistence.Embeddable;
import java.util.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class ScientificNumber {
  private static double[] POSITIVE_POWER_OF_TEN = {
    1e0, 1e1, 1e2, 1e3, 1e4, 1e5, 1e6, 1e7, 1e8, 1e9, 1e10
  };
  private static double[] NEGATIVE_POWER_OF_TEN = {1e0, 1e-1, 1e-2, 1e-3};
  private static final double EPSILON = 1e-8;
  private double mantissa;
  private int exponent;

  public ScientificNumber(double mantissa, int exponent) {
    this.mantissa = mantissa;
    this.exponent = exponent;
    normalize();
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    ScientificNumber that = (ScientificNumber) o;
    return Math.abs(mantissa - that.mantissa) < EPSILON && exponent == that.exponent;
  }

  @Override
  public int hashCode() {
    return Objects.hash(mantissa, exponent);
  }

  public boolean isZero() {
    return Math.abs(mantissa) < EPSILON;
  }

  public void multiply(double value) {
    mantissa *= value;
    normalize();
  }

  public void multiply(ScientificNumber other) {
    mantissa *= other.mantissa;
    exponent += other.exponent;
    normalize();
  }

  public void subtract(ScientificNumber other) {
    int exponentGap = exponent - other.exponent;
    if (exponentGap >= 0) {
      subtractIfGapIsPositive(other);
      return;
    }
    setZero();
  }

  public void add(ScientificNumber other) {
    int exponentGap = exponent - other.exponent;
    if (exponentGap >= 0) {
      addIfGapIsPositive(other);
      return;
    }
    setNumber(other);
  }

  public void addAll(ScientificNumber[] others) {
    for (ScientificNumber other : others) {
      add(other);
    }
  }

  private void addIfGapIsPositive(ScientificNumber other) {
    applyOperation(other, 1.0);
  }

  private void applyOperation(ScientificNumber other, double signal) {
    int exponentGap = exponent - other.exponent;
    if (exponentGap > 3) {
      return;
    }
    mantissa += signal * other.mantissa * NEGATIVE_POWER_OF_TEN[exponentGap];
    normalize();
  }

  private void setNumber(ScientificNumber other) {
    mantissa = other.mantissa;
    exponent = other.exponent;
  }

  private void setZero() {
    mantissa = 0.0;
    exponent = 0;
  }

  private void subtractIfGapIsPositive(ScientificNumber other) {
    applyOperation(other, -1.0);
  }

  private void normalize() {
    if (isZero() || mantissa < 0) {
      setZero();
      return;
    }
    double logged = Math.log10(mantissa);
    int exp = (int) Math.floor(logged + EPSILON);
    mantissa /= getPowerOfTen(exp);
    exponent += exp;
    truncate();
  }

  private double getPowerOfTen(int exp) {
    if (exp >= 0) {
      return POSITIVE_POWER_OF_TEN[exp];
    }
    return NEGATIVE_POWER_OF_TEN[-exp];
  }

  private void truncate() {
    mantissa = Math.floor((mantissa + EPSILON) * 1000.0) / 1000.0;
  }
}
