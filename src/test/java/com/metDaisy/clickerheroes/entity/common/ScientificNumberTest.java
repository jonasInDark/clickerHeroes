package com.metDaisy.clickerheroes.entity.common;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ScientificNumberTest {

  @Nested
  @DisplayName("초기화 및 정규화 (Initialization)")
  class Initialization {

    @Test
    @DisplayName("값이 0인 경우 mantissa=0.0, exponent=0 으로 초기화된다")
    void initZero() {
      ScientificNumber zero = new ScientificNumber(0, 0);
      assertThat(zero.getMantissa()).isEqualTo(0.0);
      assertThat(zero.getExponent()).isEqualTo(0);
    }

    @Test
    @DisplayName("생성 시 값이 자동으로 정규화(1.0 이상 10.0 미만)된다")
    void normalizeSuccess() {
      // 1500 -> 1.5 * 10^3
      ScientificNumber number = new ScientificNumber(1500, 0);
      assertThat(number.getMantissa()).isEqualTo(1.5);
      assertThat(number.getExponent()).isEqualTo(3);
    }
  }

  @Nested
  @DisplayName("항등원 및 0 연산 (Identity Element)")
  class Identity {

    @Test
    @DisplayName("덧셈 항등원: 0 + 0 = 0")
    void addZeroToZero() {
      ScientificNumber zero1 = new ScientificNumber(0, 0);
      ScientificNumber zero2 = new ScientificNumber(0, 0);
      zero1.add(zero2);

      assertThat(zero1.getMantissa()).isEqualTo(0.0);
      assertThat(zero1.getExponent()).isEqualTo(0);
    }

    @Test
    @DisplayName("덧셈 항등원: 0 + A = A, A + 0 = A")
    void addIdentity() {
      ScientificNumber zero = new ScientificNumber(0, 0);
      ScientificNumber a = new ScientificNumber(5.0, 2); // 500

      zero.add(a);
      assertThat(zero.getMantissa()).isEqualTo(5.0);
      assertThat(zero.getExponent()).isEqualTo(2);

      ScientificNumber b = new ScientificNumber(5.0, 2);
      ScientificNumber zero2 = new ScientificNumber(0, 0);
      b.add(zero2);
      assertThat(b.getMantissa()).isEqualTo(5.0);
      assertThat(b.getExponent()).isEqualTo(2);
    }

    @Test
    @DisplayName("곱셈 항등원: A * 1 = A")
    void multiplyIdentity() {
      ScientificNumber a = new ScientificNumber(3.5, 4);
      ScientificNumber one = new ScientificNumber(1.0, 0); // 1

      a.multiply(one);
      assertThat(a.getMantissa()).isEqualTo(3.5);
      assertThat(a.getExponent()).isEqualTo(4);
    }

    @Test
    @DisplayName("0 곱셈: A * 0 = 0")
    void multiplyByZero() {
      ScientificNumber a = new ScientificNumber(3.5, 4);
      ScientificNumber zero = new ScientificNumber(0, 0);

      a.multiply(zero);
      assertThat(a.getMantissa()).isEqualTo(0.0);
      assertThat(a.getExponent()).isEqualTo(0);
    }
  }

  @Nested
  @DisplayName("덧셈 (Addition) - 지수 차이(Gap) 검증")
  class Addition {

    @Test
    @DisplayName("지수 차이가 0 (같은 자리수)일 때 정상 덧셈된다")
    void addGap0() {
      ScientificNumber base = new ScientificNumber(1.0, 3); // 1,000
      ScientificNumber target = new ScientificNumber(2.0, 3); // 2,000

      base.add(target); // 3,000
      assertThat(base.getMantissa()).isEqualTo(3.0);
      assertThat(base.getExponent()).isEqualTo(3);
    }

    @Test
    @DisplayName("지수 차이가 1 (10배 차이)일 때 정상 덧셈된다")
    void addGap1() {
      ScientificNumber base = new ScientificNumber(1.0, 3); // 1,000
      ScientificNumber target = new ScientificNumber(5.0, 2); // 500

      base.add(target); // 1,500
      assertThat(base.getMantissa()).isEqualTo(1.5);
      assertThat(base.getExponent()).isEqualTo(3);
    }

    @Test
    @DisplayName("지수 차이가 2 (100배 차이)일 때 정상 덧셈된다")
    void addGap2() {
      ScientificNumber base = new ScientificNumber(1.0, 3); // 1,000
      ScientificNumber target = new ScientificNumber(5.0, 1); // 50

      base.add(target); // 1,050
      assertThat(base.getMantissa()).isEqualTo(1.05);
      assertThat(base.getExponent()).isEqualTo(3);
    }

    @Test
    @DisplayName("지수 차이가 3 (1000배 차이)일 때 정상 덧셈된다")
    void addGap3() {
      ScientificNumber base = new ScientificNumber(1.0, 3); // 1,000
      ScientificNumber target = new ScientificNumber(5.0, 0); // 5

      base.add(target); // 1,005
      assertThat(base.getMantissa()).isEqualTo(1.005);
      assertThat(base.getExponent()).isEqualTo(3);
    }

    @Test
    @DisplayName("지수 차이가 3을 초과(10000배 이상)하면 너무 작은 값이므로 무시된다")
    void addGapIgnored() {
      ScientificNumber base = new ScientificNumber(1.0, 4); // 10,000
      ScientificNumber target = new ScientificNumber(9.0, 0); // 9

      base.add(target); // 10,000 유지
      assertThat(base.getMantissa()).isEqualTo(1.0);
      assertThat(base.getExponent()).isEqualTo(4);
    }
  }

  @Nested
  @DisplayName("다중 덧셈 (AddAll)")
  class AddAll {

    @Test
    @DisplayName("여러 개의 객체를 전달하면 순차적으로 모두 합산된다")
    void addAll_Success() {
      ScientificNumber base = new ScientificNumber(1.0, 3); // 1,000

      List<ScientificNumber> targets = List.of(
          new ScientificNumber(2.0, 3), // 2,000
          new ScientificNumber(3.0, 3), // 3,000
          new ScientificNumber(4.0, 3)  // 4,000
      );

      base.addAll(targets); // 10,000 -> 1.0e4
      assertThat(base.getMantissa()).isEqualTo(1.0);
      assertThat(base.getExponent()).isEqualTo(4);
    }

    @Test
    @DisplayName("addAll 실행 중에도 지수 차이가 큰 값은 자동으로 합산에서 제외된다")
    void addAll_IgnoreLargeGapInLoop() {
      ScientificNumber base = new ScientificNumber(1.0, 5); // 100,000

      List<ScientificNumber> targets = List.of(
          new ScientificNumber(2.0, 5), // 200,000 (더해짐)
          new ScientificNumber(9.0, 1), // 90 (무시됨)
          new ScientificNumber(5.0, 4)  // 50,000 (더해짐)
      );

      base.addAll(targets); // 350,000 -> 3.5e5
      assertThat(base.getMantissa()).isEqualTo(3.5);
      assertThat(base.getExponent()).isEqualTo(5);
    }
  }

  @Nested
  @DisplayName("곱셈 및 뺄셈 (Multiplication & Subtraction)")
  class MultiplyAndSubtract {

    @Test
    @DisplayName("객체 간 곱셈 시 가수는 곱해지고 지수는 더해진다")
    void multiplyByInstance() {
      ScientificNumber base = new ScientificNumber(2.0, 3); // 2,000
      ScientificNumber target = new ScientificNumber(3.0, 2); // 300

      base.multiply(target); // 600,000 -> 6.0e5
      assertThat(base.getMantissa()).isEqualTo(6.0);
      assertThat(base.getExponent()).isEqualTo(5);
    }

    @Test
    @DisplayName("상수(Scalar) 곱셈 시 가수에 값이 곱해진 후 정규화된다")
    void multiplyByScalar() {
      ScientificNumber base = new ScientificNumber(2.5, 2); // 250
      base.multiply(4.0); // 1000 -> 1.0e3

      assertThat(base.getMantissa()).isEqualTo(1.0);
      assertThat(base.getExponent()).isEqualTo(3);
    }

    @Test
    @DisplayName("뺄셈 시 정상적으로 값이 차감된다")
    void subtractSuccess() {
      ScientificNumber base = new ScientificNumber(3.0, 3); // 3,000
      ScientificNumber target = new ScientificNumber(1.0, 3); // 1,000

      base.subtract(target); // 2,000 -> 2.0e3
      assertThat(base.getMantissa()).isEqualTo(2.0);
      assertThat(base.getExponent()).isEqualTo(3);
    }

    @Test
    @DisplayName("뺄셈 시에도 지수 차이가 3을 초과하면 연산을 무시한다")
    void subtractIgnored() {
      ScientificNumber base = new ScientificNumber(1.0, 5); // 100,000
      ScientificNumber target = new ScientificNumber(5.0, 1); // 50

      base.subtract(target); // 100,000 유지
      assertThat(base.getMantissa()).isEqualTo(1.0);
      assertThat(base.getExponent()).isEqualTo(5);
    }
  }

  @Nested
  @DisplayName("Object 메서드 (equals, hashCode, toString)")
  class ObjectMethods {

    @Test
    @DisplayName("toString()은 '가수(소수점 3자리) x e지수' 포맷으로 출력된다")
    void customToString() {
      ScientificNumber num1 = new ScientificNumber(1.5, 3);
      ScientificNumber num2 = new ScientificNumber(5.0, 0);

      assertThat(num1.toString()).isEqualTo("1.500 x e3");
      assertThat(num2.toString()).isEqualTo("5.000 x e0");
    }

    @Test
    @DisplayName("정규화된 결과가 같은 두 객체는 equals가 true이고 hashCode가 동일하다")
    void equalsAndHashCode_Same() {
      // 25.0 * 10^4 -> 정규화 -> 2.5 * 10^5
      ScientificNumber num1 = new ScientificNumber(25.0, 4);
      // 2.5 * 10^5 -> 정규화 -> 2.5 * 10^5
      ScientificNumber num2 = new ScientificNumber(2.5, 5);

      assertThat(num1).isEqualTo(num2);
      assertThat(num1.hashCode()).isEqualTo(num2.hashCode());
    }

    @Test
    @DisplayName("가수나 지수 중 하나라도 다르면 equals는 false이다")
    void equals_Different() {
      ScientificNumber base = new ScientificNumber(2.0, 3);
      ScientificNumber diffMantissa = new ScientificNumber(3.0, 3);
      ScientificNumber diffExponent = new ScientificNumber(2.0, 4);

      assertThat(base).isNotEqualTo(diffMantissa);
      assertThat(base).isNotEqualTo(diffExponent);
      assertThat(base).isNotEqualTo(null);
      assertThat(base).isNotEqualTo(new Object());
    }
  }
}
