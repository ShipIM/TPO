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
            10 * -Math.PI, 5 * -Math.PI, 5 * Math.PI, 10 * Math.PI
    })
    public void checkCalculate(double x) {
        Assertions.assertEquals(Math.sin(x), sinus.calculate(x), 0.0001);
    }

    @ParameterizedTest
    @ValueSource(doubles = {
            Double.NaN, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY
    })
    public void calculateThrowsExceptionOnExceptionalX(double x) {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> sinus.calculate(x));
    }

    @Test
    public void calculateWithBigNThrowsException() {
        sinus.setN(Integer.MAX_VALUE);
        sinus.setPrecision(1000);

        Assertions.assertThrows(ArithmeticException.class,
                () -> sinus.calculate(Math.PI));
    }

    @ParameterizedTest
    @ValueSource(ints = {
            -1, 0
    })
    public void calculateWithNLessThanOneThrowsException(int n) {
        sinus.setN(n);

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> sinus.calculate(Math.PI));
    }

}
