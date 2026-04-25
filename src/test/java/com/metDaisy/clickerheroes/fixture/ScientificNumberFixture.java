package com.metDaisy.clickerheroes.fixture;

import static org.instancio.Select.all;

import com.metDaisy.clickerheroes.entity.common.ScientificNumber;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.instancio.Instancio;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ScientificNumberFixture {

  public static ScientificNumber create() {
    return Instancio.of(ScientificNumber.class)
        .supply(
            all(ScientificNumber.class),
            () -> new ScientificNumber(NumberFixture.mantissa(), NumberFixture.exponent()))
        .create();
  }
}
