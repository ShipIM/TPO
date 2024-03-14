package org.example.first.math;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class SinusTest {

    private final Sinus sinus = new Sinus();

    @ParameterizedTest
    @ValueSource(doubles = {
            -Math.PI, -Math.PI / 2, 0, Math.PI / 2, Math.PI,
            Math.PI / 6, Math.PI / 4, Math.PI / 3, 3 * Math.PI / 2, 2 * Math.PI,
            -Math.PI / 6, -Math.PI / 4, -Math.PI / 3, 3 * -Math.PI / 2, 2 * -Math.PI,
            10 * -Math.PI, 5 * -Math.PI, 5 * Math.PI, 10 * Math.PI,
            Double.MIN_VALUE
    })
    public void checkCalculate(double x) {
        Assertions.assertEquals(Math.sin(x), sinus.calculate(x, 50), 0.00001);
    }

    @ParameterizedTest
    @ValueSource(doubles = {
            Double.NaN, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY
    })
    public void calculateThrowsExceptionOnExceptionalX(double x) {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> sinus.calculate(x, 50));
    }

    @Test
    public void calculateWithBigNThrowsException() {
        Assertions.assertThrows(ArithmeticException.class,
                () -> sinus.calculate(Math.PI, Integer.MAX_VALUE));
    }

    @ParameterizedTest
    @ValueSource(ints = {
            -1, 0
    })
    public void calculateWithNLessThanOneThrowsException(int n) {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> sinus.calculate(Math.PI, n));
    }

}
