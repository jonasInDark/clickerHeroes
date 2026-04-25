package com.metDaisy.clickerheroes.entity.common;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import static org.junit.jupiter.api.Assertions.*;

import com.metDaisy.clickerheroes.fixture.NumberFixture;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
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
  @MethodSource("provideIntegers")
  void successToNormalize(int value) {
    // Given
    ScientificNumber actual = new ScientificNumber(value, 0);

    // When
    int expectedExponent = (int) Math.log10(value);
    double rawMantissa = value / Math.pow(10, expectedExponent);
    double expectedMantissa = Math.floor((rawMantissa + 1e-8) * 1000.0) / 1000.0;

    // Then
    assertThat(actual.getExponent()).as("지수 계산 오류").isEqualTo(expectedExponent);
    assertThat(actual.getMantissa()).as("가수 범위 오류").isGreaterThanOrEqualTo(1.0).isLessThan(10.0);
    assertThat(actual.getMantissa()).as("가수 정밀도 오류").isCloseTo(expectedMantissa, within(1e-8));
  }

  @Nested
  @DisplayName("multiply by scalar")
  class MultiplyByScalar {
    @Test
    @DisplayName("314 x 8 = 2.512 x 10**3")
    void multiplyByScalar1() {
      multiplyByScalar(8);
    }

    @Test
    @DisplayName("314 x 88888 = 2.791 x 10**7")
    void multiplyByScalar2() {
      multiplyByScalar(88888);
    }
  }

  @Nested
  @DisplayName("multiply by instance")
  class MultiplyByInstance {
    @Test
    @DisplayName("314 x 314 = 9.859 x 10**4")
    void multiplyByInstance1() {
      multiplyBySelf(314);
    }

    @Test
    @DisplayName("3141 x 3141 = 9.865 x 10**8")
    void multiplyByInstance2() {
      multiplyBySelf(3141);
    }

    @Test
    @DisplayName("3.14 x 10**2 x 3.141 x 10**6 = 9.862 x 10**8")
    void multiplyByInstance3() {
      ScientificNumber inst1 = createInstanceByInteger(314);
      ScientificNumber inst2 = createInstanceByInteger(3141000);
      inst1.multiply(inst2);
      ScientificNumber expected = new ScientificNumber(9.862, 8);
      assertEquals(expected, inst1);
    }
  }

  @Nested
  @DisplayName("subtract")
  class Subtract {
    @Test
    @DisplayName("3141 - 3140 = 1")
    void subtract1() {
      ScientificNumber inst1 = createInstanceByInteger(3141);
      ScientificNumber inst2 = createInstanceByInteger(3140);
      inst1.subtract(inst2);
      ScientificNumber expected = createInstanceByInteger(1);
      assertEquals(expected, inst1);
    }

    @Test
    @DisplayName("3.141 x 10**6 - 3.14 x 10**2 = 3.141 x 10**6")
    void subtract2() {
      ScientificNumber inst1 = createInstanceByInteger(3141000);
      ScientificNumber inst2 = createInstanceByInteger(314);
      inst1.subtract(inst2);
      ScientificNumber expected = createInstanceByInteger(3141000);
      assertEquals(expected, inst1);
    }

    @Test
    @DisplayName("3.14 x 10**2 - 3.141 x 10**6 = 0.0")
    void subtract3() {
      ScientificNumber inst1 = createInstanceByInteger(314);
      ScientificNumber inst2 = createInstanceByInteger(3141000);
      inst1.subtract(inst2);
      ScientificNumber expected = createInstanceByInteger(0);
      assertEquals(expected, inst1);
    }

    @Test
    @DisplayName("3141 - 1 = 3.14 x 10**3")
    void subtract4() {
      ScientificNumber inst1 = createInstanceByInteger(3141);
      ScientificNumber inst2 = createInstanceByInteger(1);
      inst1.subtract(inst2);
      ScientificNumber expected = new ScientificNumber(3140, 0);
      assertEquals(expected, inst1);
    }

    @Test
    @DisplayName("3141 - 41 = 3.1 x 10**3")
    void subtract5() {
      ScientificNumber inst1 = createInstanceByInteger(3141);
      ScientificNumber inst2 = createInstanceByInteger(41);
      inst1.subtract(inst2);
      ScientificNumber expected = createInstanceByInteger(3100);
      assertEquals(expected, inst1);
    }

    @Test
    @DisplayName("3141 - 141 = 3.0 x 10**3")
    void subtract6() {
      ScientificNumber inst1 = createInstanceByInteger(3141);
      ScientificNumber inst2 = createInstanceByInteger(141);
      inst1.subtract(inst2);
      ScientificNumber expected = createInstanceByInteger(3000);
      assertEquals(expected, inst1);
    }

    @Test
    @DisplayName("3141 - 3141 = 0")
    void subtract7() {
      ScientificNumber inst1 = createInstanceByInteger(3141);
      ScientificNumber inst2 = createInstanceByInteger(3141);
      inst1.subtract(inst2);
      ScientificNumber expected = createInstanceByInteger(0);
      ;
      assertEquals(expected, inst1);
    }
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
        createInstanceByInteger(3141), createInstanceByInteger(3141), createInstanceByInteger(3141)
      };
      inst1.addAll(inst2);
      ScientificNumber expected = createInstanceByInteger(9423);
      assertEquals(expected, inst1);
    }
  }

  private void multiplyBySelf(int value) {
    ScientificNumber actual = createInstanceByInteger(value);
    ScientificNumber inst = createInstanceByInteger(value);
    actual.multiply(inst);
    ScientificNumber expected = createInstanceByInteger(value * value);
    assertEquals(expected, actual);
  }

  private void multiplyByScalar(int scalar) {
    int source = 314;
    int target = source * scalar;
    ScientificNumber actual = createInstanceByInteger(source);
    actual.multiply(scalar);
    ScientificNumber expected = createInstanceByInteger(target);
    assertEquals(expected, actual);
  }

  private ScientificNumber createInstanceByInteger(int value) {
    return new ScientificNumber(value, 0);
  }

  private static Stream<Integer> provideIntegers() {
    return Stream.generate(NumberFixture::anInt).limit(10);
  }
}
