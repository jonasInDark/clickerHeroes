package com.dbrvkf.clickerheroes.entity.common;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ScientificNumberTest {

  @Nested
  @DisplayName("normalize")
  class NormalizeTest {
    @Test
    @DisplayName("3141592 = 3.141 x 10**6")
    void normalize1() {
      int source = 3141592;
      ScientificNumber actual = new ScientificNumber(source, 0);
      ScientificNumber expected = new ScientificNumber(3.141592, 6);
      assertEquals(expected, actual);
    }

    @Test
    @DisplayName("1 = 1 x 10**0")
    void normalize2() {
      int source = 1;
      ScientificNumber actual = createInstanceByInteger(source);
      ScientificNumber expected = new ScientificNumber(1.0, 0);
      assertEquals(expected, actual);
    }

    @Test
    @DisplayName("0 = 0 x 10**0")
    void normalize3() {
      int source = 0;
      ScientificNumber actual = createInstanceByInteger(source);
      ScientificNumber expected = new ScientificNumber(0.0, 0);
      assertEquals(expected, actual);
    }
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
      ScientificNumber expected = createInstanceByInteger(0);;
      assertEquals(expected, inst1);
    }
  }

  private static void multiplyBySelf(int value) {
    ScientificNumber actual = createInstanceByInteger(value);
    ScientificNumber inst = createInstanceByInteger(value);
    actual.multiply(inst);
    ScientificNumber expected = createInstanceByInteger(value * value);
    assertEquals(expected, actual);
  }

  private static void multiplyByScalar(int scalar) {
    int source = 314;
    int target = source * scalar;
    ScientificNumber actual = createInstanceByInteger(source);
    actual.multiply(scalar);
    ScientificNumber expected = createInstanceByInteger(target);
    assertEquals(expected, actual);
  }

  private static ScientificNumber createInstanceByInteger(int value) {
    return new ScientificNumber(value, 0);
  }
}
