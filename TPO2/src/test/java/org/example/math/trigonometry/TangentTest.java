package org.example.math.trigonometry;

import org.example.first.math.Sinus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TangentTest {

    @Mock
    private Sinus mockedSinus;
    @Mock
    private Cosine mockedCosine;
    @InjectMocks
    private Tangent mockedTangent;

    private static Sinus sinus;
    private static Cosine cosine;
    private static Tangent tangent;

    @BeforeAll
    private static void init() {
        sinus = new Sinus();
        cosine = new Cosine(sinus);
        tangent = new Tangent(sinus, cosine);
    }

    @Test
    public void calculateWithMocks() {
        Mockito.when(mockedSinus.calculate(Mockito.eq((double) 1))).thenReturn(0.84147);
        Mockito.when(mockedCosine.calculate(Mockito.eq((double) 1))).thenReturn(0.54030);

        double result = mockedTangent.calculate(1);

        Mockito.verify(mockedSinus, Mockito.times(1)).calculate(Mockito.anyDouble());
        Mockito.verify(mockedCosine, Mockito.times(1)).calculate(Mockito.anyDouble());
        Assertions.assertEquals(1.55740, result, 0.001);
    }

    @Test
    public void calculateWithMocksZeroDivision() {
        Mockito.when(mockedCosine.calculate(Mockito.anyDouble())).thenReturn((double) 0);

        Assertions.assertThrows(ArithmeticException.class, () -> mockedTangent.calculate(0));
    }

    @ParameterizedTest
    @ValueSource(doubles = {
            -Math.PI, 0, Math.PI,
            Math.PI / 6, Math.PI / 4, Math.PI / 3, 2 * Math.PI,
            -Math.PI / 6, -Math.PI / 4, -Math.PI / 3, 2 * -Math.PI,
            10 * -Math.PI, 5 * -Math.PI, 5 * Math.PI, 10 * Math.PI
    })
    public void calculate(double x) {
        Assertions.assertEquals(Math.tan(x), tangent.calculate(x), 0.001);
    }

    @ParameterizedTest
    @ValueSource(doubles = {
            -Math.PI / 2, Math.PI / 2,
            3 * Math.PI / 2, 3 * -Math.PI / 2
    })
    public void calculateZeroDivision(double x) {
        Assertions.assertThrows(ArithmeticException.class, () -> tangent.calculate(x));
    }

    @ParameterizedTest
    @ValueSource(doubles = {
            Double.NaN, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY,
    })
    public void calculateThrowsExceptionOnExceptionalX(double x) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> tangent.calculate(x));
    }

}
