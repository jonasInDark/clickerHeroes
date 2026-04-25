package com.metDaisy.clickerheroes.fixture;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.instancio.Instancio;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class NumberFixture {

  public static int exponent() {
    return Instancio.gen().ints().range(0, 100).get();
  }

  public static double mantissa() {
    return Instancio.gen().doubles().range(1.0, 10.0).get();
  }

  public static int anInt() {
    return Instancio.gen().ints().range(0, 100_000_000).get();
  }
}
