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
public class CosineTest {

    @Mock
    private Sinus mockedSinus;
    @InjectMocks
    private Cosine mockedCosine;

    private static Sinus sinus;
    private static Cosine cosine;

    @BeforeAll
    private static void init() {
        sinus = new Sinus();
        cosine = new Cosine(sinus);
    }

    @Test
    public void calculateWithMocks() {
        Mockito.when(mockedSinus.calculate(Mockito.eq(Math.PI))).thenReturn((double) 0);

        double result = mockedCosine.calculate(Math.PI);

        Mockito.verify(mockedSinus, Mockito.times(1)).calculate(Mockito.anyDouble());
        Assertions.assertEquals(-1, result);
    }

    @ParameterizedTest
    @ValueSource(doubles = {
            -Math.PI, -Math.PI / 2, 0, Math.PI / 2, Math.PI,
            Math.PI / 6, Math.PI / 4, Math.PI / 3, 3 * Math.PI / 2, 2 * Math.PI,
            -Math.PI / 6, -Math.PI / 4, -Math.PI / 3, 3 * -Math.PI / 2, 2 * -Math.PI,
            10 * -Math.PI, 5 * -Math.PI, 5 * Math.PI, 10 * Math.PI
    })
    public void calculate(double x) {
        Assertions.assertEquals(Math.cos(x), cosine.calculate(x), 0.001);
    }

    @ParameterizedTest
    @ValueSource(doubles = {
            Double.NaN, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY,
    })
    public void calculateThrowsExceptionOnExceptionalX(double x) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> cosine.calculate(x));
    }

}
