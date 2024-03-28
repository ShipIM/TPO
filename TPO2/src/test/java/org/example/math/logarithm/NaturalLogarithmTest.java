package org.example.math.logarithm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class NaturalLogarithmTest {

    private final NaturalLogarithm naturalLogarithm = new NaturalLogarithm();

    @ParameterizedTest
    @ValueSource(doubles = {
            2, 3, 10,
            0.1, 0.5, 0.9,
            1
    })
    public void calculate(double x) {
        Assertions.assertEquals(Math.log(x), naturalLogarithm.calculate(x), 0.001);
    }

    @ParameterizedTest
    @ValueSource(doubles = {
            Double.NaN, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY,
            -1, 0
    })
    public void calculateThrowsExceptionOnExceptionalX(double x) {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> naturalLogarithm.calculate(x));
    }

    @ParameterizedTest
    @ValueSource(ints = {
            -1, 0
    })
    public void calculateWithNLessThanOneThrowsException(int n) {
        naturalLogarithm.setN(n);

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> naturalLogarithm.calculate(Math.PI));
    }

    @ParameterizedTest
    @ValueSource(doubles = {
            2, 3, 10,
            0.1, 0.5, 0.9,
            1
    })
    public void calculateWithHighPrecision(double x) {
        NaturalLogarithm logarithm = new NaturalLogarithm(50, 1000);

        Assertions.assertEquals(Math.log(x), logarithm.calculate(x), 0.001);
    }

}
