package org.example.math.logarithm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BaseLogarithmTest {

    @Mock
    private NaturalLogarithm mockedNaturalLogarithm;
    @InjectMocks
    private BaseLogarithm mockedBaseLogarithm;

    private static NaturalLogarithm naturalLogarithm;
    private static BaseLogarithm baseLogarithm;

    @BeforeAll
    private static void init() {
        naturalLogarithm = new NaturalLogarithm();
        baseLogarithm = new BaseLogarithm(naturalLogarithm);
    }

    @Test
    public void calculateWithMocks() {
        Mockito.when(mockedNaturalLogarithm.calculate(Mockito.eq((double) 3))).thenReturn(1.09861);
        Mockito.when(mockedNaturalLogarithm.calculate(Mockito.eq((double) 5))).thenReturn(1.60943);

        mockedBaseLogarithm.setBase(3);

        double result = mockedBaseLogarithm.calculate(5);

        Mockito.verify(mockedNaturalLogarithm, Mockito.times(2)).calculate(Mockito.anyDouble());
        Assertions.assertEquals(1.46497, result, 0.001);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "3, 1, 0",
            "3, 5, 1.46497",
            "3, 0.5, -0.63092"
    })
    public void calculateActualValue(int base, double x, double result) {
        baseLogarithm.setBase(base);

        Assertions.assertEquals(result, baseLogarithm.calculate(x), 0.001);
    }

    @ParameterizedTest
    @ValueSource(doubles = {
            Double.NaN, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY,
            -1, 0
    })
    public void calculateThrowsExceptionOnExceptionalX(double x) {
        baseLogarithm.setBase(1);

        Assertions.assertThrows(IllegalArgumentException.class, () -> baseLogarithm.calculate(x));
    }

    @Test
    public void calculateBaseNotSet() {
        mockedBaseLogarithm.setBase(null);

        Assertions.assertThrows(IllegalArgumentException.class, () -> mockedBaseLogarithm.calculate(0));
    }

    @Test
    public void calculateBaseIllegalValue() {
        mockedBaseLogarithm.setBase(0);

        Assertions.assertThrows(IllegalArgumentException.class, () -> mockedBaseLogarithm.calculate(0));
    }

}
