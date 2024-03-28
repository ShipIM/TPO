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
public class CotangentTest {

    @Mock
    private Tangent mockedTangent;
    @InjectMocks
    private Cotangent mockedCotangent;

    private static Sinus sinus;
    private static Cosine cosine;
    private static Tangent tangent;
    private static Cotangent cotangent;

    @BeforeAll
    private static void init() {
        sinus = new Sinus();
        cosine = new Cosine(sinus);
        tangent = new Tangent(sinus, cosine);
        cotangent = new Cotangent(tangent);
    }

    @Test
    public void calculateWithMocks() {
        Mockito.when(mockedTangent.calculate(Mockito.eq((double) 2))).thenReturn(-2.18503);

        double result = mockedCotangent.calculate(2);

        Mockito.verify(mockedTangent, Mockito.times(1)).calculate(Mockito.anyDouble());
        Assertions.assertEquals(-0.45765, result, 0.001);
    }

    @Test
    public void calculateWithMocksZeroDivision() {
        Mockito.when(mockedTangent.calculate(Mockito.anyDouble())).thenReturn((double) 0);

        Assertions.assertThrows(ArithmeticException.class, () -> mockedCotangent.calculate(0));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "0.52359, 1.73208",
            "0.78539, 1",
            "1.04719, 0.57736",
            "-0.52359, -1.73208",
            "-0.78539, -1",
            "-1.04719, -0.57736"
    })
    public void calculate(double x, double result) {
        Assertions.assertEquals(result, cotangent.calculate(x), 0.001);
    }

    @ParameterizedTest
    @ValueSource(doubles = {
            -Math.PI, 0, Math.PI,
            -Math.PI / 2, Math.PI / 2,
            3 * Math.PI / 2, 3 * -Math.PI / 2
    })
    public void calculateZeroDivision(double x) {
        Assertions.assertThrows(ArithmeticException.class, () -> cotangent.calculate(x));
    }

    @ParameterizedTest
    @ValueSource(doubles = {
            Double.NaN, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY,
    })
    public void calculateThrowsExceptionOnExceptionalX(double x) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> cotangent.calculate(x));
    }

}
