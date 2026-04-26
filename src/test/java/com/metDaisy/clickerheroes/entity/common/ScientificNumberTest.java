package com.metDaisy.clickerheroes.entity.common;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.metDaisy.clickerheroes.fixture.NumberFixture;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ScientificNumberTest {

  @Nested
  @DisplayName("init ZERO")
  class Zero {

    @Test
    @DisplayName("값이 0인 경우 객체 생성 시 mantissa=0.0, exponent=0 와 같다")
    void successToInitZero() {
      ScientificNumber zero = new ScientificNumber(0, 0);
      assertAll(
          () -> assertEquals(0, zero.getExponent()), () -> assertEquals(0.0, zero.getMantissa()));
    }
  }

  @ParameterizedTest(name = "0보다 큰 임의의 정수에 대해 객체 생성 성공, {0}")
  @MethodSource("provideBigIntegers")
  void successToNormalize(int value) {
    // given
    ScientificNumber expected = new ScientificNumber(value, 0);

    // when & then
    validateEqual(expected, value);
  }

  @ParameterizedTest(
      name = "0보다 큰 임의의 정수에 대해 객체 생성 후 0보다 큰 임의의 정수를 곱할 수 있다\n" + "value={0}, scalar={1}")
  @MethodSource("provideSmallIntegerPairs")
  void multiplyByScalar(int value, int scalar) {
    // given
    ScientificNumber expected = new ScientificNumber(value, 0);
    expected.multiply(scalar);

    // when & then
    validateEqual(expected, value * scalar);
  }

  @ParameterizedTest(
      name = """
          0보다 큰 임의의 정수에 대해 객체 생성 후 객체 간 곱셈을 할 수 있다
          두 정수의 곱이 integer 범위 안에 있는 경우에 대해서 테스트한다
          long 타입을 객체로 만드는 경우가 없다
          value1={0}, value2={1}""")
  @MethodSource("provideSmallIntegerPairs")
  void multiplyByInstance(int value1, int value2) {
    // given
    ScientificNumber expected = new ScientificNumber(value1, 0);
    ScientificNumber other = new ScientificNumber(value2, 0);
    expected.multiply(other);

    // when & then
    validateEqual(expected, value1 * value2);
  }

  @ParameterizedTest(name = "0보다 큰 임의의 정수에 대해 객체 생성 후 객체 간 뺄셈을 할 수 있다\n"
      + "value1={0}, value2={1}")
  @MethodSource("provideSmallIntegerPairs")
  void subtractByInstance(int value1, int value2) {
    // given
    int big = Math.max(value1, value2);
    int small = Math.min(value1, value2);
    ScientificNumber expected = new ScientificNumber(big, 0);
    ScientificNumber other = new ScientificNumber(small, 0);
    expected.subtract(other);

    // when & then
    validateEqual(expected, big - small);
  }

  @Nested
  @DisplayName("add")
  class Add {

    @Test
    @DisplayName("0 + 0 = 0")
    void add1() {
      ScientificNumber inst1 = createInstanceByInteger(0);
      ScientificNumber inst2 = createInstanceByInteger(0);
      inst1.add(inst2);
      ScientificNumber expected = createInstanceByInteger(0);
      assertEquals(expected, inst1);
    }

    @Test
    @DisplayName("1 + 0 = 0")
    void add2() {
      ScientificNumber inst1 = createInstanceByInteger(1);
      ScientificNumber inst2 = createInstanceByInteger(0);
      inst1.add(inst2);
      ScientificNumber expected = createInstanceByInteger(1);
      assertEquals(expected, inst1);
    }

    @Test
    @DisplayName("3141 + 1 = 3.142 x 10**3")
    void add3() {
      ScientificNumber inst1 = createInstanceByInteger(3141);
      ScientificNumber inst2 = createInstanceByInteger(1);
      inst1.add(inst2);
      ScientificNumber expected = createInstanceByInteger(3142);
      assertEquals(expected, inst1);
    }

    @Test
    @DisplayName("3141 + 41 = 3.182 x 10**3")
    void add4() {
      ScientificNumber inst1 = createInstanceByInteger(3141);
      ScientificNumber inst2 = createInstanceByInteger(41);
      inst1.add(inst2);
      ScientificNumber expected = createInstanceByInteger(3182);
      assertEquals(expected, inst1);
    }

    @Test
    @DisplayName("3141 + 141 = 3.282 x 10**3")
    void add5() {
      ScientificNumber inst1 = createInstanceByInteger(3141);
      ScientificNumber inst2 = createInstanceByInteger(141);
      inst1.add(inst2);
      ScientificNumber expected = createInstanceByInteger(3282);
      assertEquals(expected, inst1);
    }

    @Test
    @DisplayName("3141 + 3141 = 6.282 x 10**3")
    void add6() {
      ScientificNumber inst1 = createInstanceByInteger(3141);
      ScientificNumber inst2 = createInstanceByInteger(3141);
      inst1.add(inst2);
      ScientificNumber expected = createInstanceByInteger(6282);
      assertEquals(expected, inst1);
    }

    @Test
    @DisplayName("3141000 + 3141 = 3.144 x 10**6")
    void add7() {
      ScientificNumber inst1 = createInstanceByInteger(3141000);
      ScientificNumber inst2 = createInstanceByInteger(3141);
      inst1.add(inst2);
      ScientificNumber expected = createInstanceByInteger(3144000);
      assertEquals(expected, inst1);
    }

    @Test
    @DisplayName("3141592 + 3141592 = 6.282 x 10**6")
    void add8() {
      ScientificNumber inst1 = createInstanceByInteger(3141592);
      ScientificNumber inst2 = createInstanceByInteger(3141592);
      inst1.add(inst2);
      ScientificNumber expected = createInstanceByInteger(6282000);
      assertEquals(expected, inst1);
    }
  }

  @Nested
  @DisplayName("addAll")
  class AddAll {

    @Test
    @DisplayName("0 + {0, 0, 0} = 0")
    void addAll1() {
      ScientificNumber inst1 = createInstanceByInteger(0);
      ScientificNumber[] inst2 = {
          createInstanceByInteger(0), createInstanceByInteger(0), createInstanceByInteger(0)
      };
      inst1.addAll(inst2);
      ScientificNumber expected = createInstanceByInteger(0);
      assertEquals(expected, inst1);
    }

    @Test
    @DisplayName("0 + {3141, 3141, 3141} = 9423")
    void addAll2() {
      ScientificNumber inst1 = createInstanceByInteger(0);
      ScientificNumber[] inst2 = {
          createInstanceByInteger(3141), createInstanceByInteger(3141),
          createInstanceByInteger(3141)
      };
      inst1.addAll(inst2);
      ScientificNumber expected = createInstanceByInteger(9423);
      assertEquals(expected, inst1);
    }
  }

  private void validateEqual(ScientificNumber expected, int actual) {
    // When
    int expectedExponent = (int) Math.log10(actual);
    double rawMantissa = actual / Math.pow(10, expectedExponent);
    double expectedMantissa = Math.floor((rawMantissa + 1e-8) * 1000.0) / 1000.0;

    // Then
    assertThat(expected.getExponent()).as("지수 계산 오류").isEqualTo(expectedExponent);
    assertThat(expected.getMantissa()).as("가수 범위 오류").isGreaterThanOrEqualTo(1.0).isLessThan(10.0);
    assertThat(expected.getMantissa()).as("가수 정밀도 오류").isCloseTo(expectedMantissa, within(1e-8));
  }

  private ScientificNumber createInstanceByInteger(int value) {
    return new ScientificNumber(value, 0);
  }

  private static Stream<Integer> provideBigIntegers() {
    return Stream.generate(NumberFixture::bigInt).limit(10);
  }

  private static Stream<Arguments> provideSmallIntegerPairs() {
    return Stream.generate(() -> Arguments.of(NumberFixture.smallInt(), NumberFixture.smallInt()))
        .limit(10);
  }
}
