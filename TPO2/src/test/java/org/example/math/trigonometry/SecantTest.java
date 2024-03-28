package org.example.math.trigonometry;

import org.example.first.math.Sinus;
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
public class SecantTest {

    @Mock
    private Cosine mockedCosine;
    @InjectMocks
    private Secant mockedSecant;

    private static Sinus sinus;
    private static Cosine cosine;
    private static Secant secant;

    @BeforeAll
    private static void init() {
        sinus = new Sinus();
        cosine = new Cosine(sinus);
        secant = new Secant(cosine);
    }

    @Test
    public void calculateWithMocks() {
        Mockito.when(mockedCosine.calculate(Mockito.eq((double) 0))).thenReturn((double) 1);

        double result = mockedSecant.calculate(0);

        Mockito.verify(mockedCosine, Mockito.times(1)).calculate(Mockito.anyDouble());
        Assertions.assertEquals(1, result);
    }

    @Test
    public void calculateWithMocksZeroDivision() {
        Mockito.when(mockedCosine.calculate(Mockito.anyDouble())).thenReturn((double) 0);

        Assertions.assertThrows(ArithmeticException.class, () -> mockedSecant.calculate(0));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "-3.14159, -1",
            "0, 1",
            "3.14159, -1",
            "0.52359, 1.15470",
            "0.78539, 1.41421",
            "1.04719, 2",
            "6.28318, 1",
            "-0.52359, 1.15470",
            "-0.78539, 1.41421",
            "-1.04719, 2",
            "-6.28318, 1",
            "-31.41592, 1",
            "-15.70796, -1",
            "15.70796, -1",
            "31.41592, 1"
    })
    public void calculate(double x, double result) {
        Assertions.assertEquals(result, secant.calculate(x), 0.001);
    }

    @ParameterizedTest
    @ValueSource(doubles = {
            -Math.PI / 2, Math.PI / 2,
            3 * Math.PI / 2, 3 * -Math.PI / 2
    })
    public void calculateZeroDivision(double x) {
        Assertions.assertThrows(ArithmeticException.class, () -> secant.calculate(x));
    }

    @ParameterizedTest
    @ValueSource(doubles = {
            Double.NaN, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY,
    })
    public void calculateThrowsExceptionOnExceptionalX(double x) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> secant.calculate(x));
    }

}
