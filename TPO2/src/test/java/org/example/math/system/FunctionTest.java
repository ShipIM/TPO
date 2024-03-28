package org.example.math.system;

import org.example.first.math.Sinus;
import org.example.math.logarithm.BaseLogarithm;
import org.example.math.logarithm.NaturalLogarithm;
import org.example.math.trigonometry.Cosine;
import org.example.math.trigonometry.Cotangent;
import org.example.math.trigonometry.Secant;
import org.example.math.trigonometry.Tangent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FunctionTest {

    private static Sinus mockedSinus;
    private static NaturalLogarithm mockedNaturalLogarithm;
    private static Cosine mockedCosine;
    private static Secant mockedSecant;
    private static Tangent mockedTangent;
    private static Cotangent mockedCotangent;
    private static BaseLogarithm mockedBase3;
    private static BaseLogarithm mockedBase5;
    private static BaseLogarithm mockedBase10;
    private static Function mockedFunction;

    private static Sinus sinus;
    private static NaturalLogarithm naturalLogarithm;
    private static Function function;

    @BeforeAll
    private static void init() {
        mockedSinus = Mockito.mock();
        mockedNaturalLogarithm = Mockito.mock();

        mockedCosine = Mockito.mock();
        mockedSecant = Mockito.mock();
        mockedTangent = Mockito.mock();
        mockedCotangent = Mockito.mock();

        mockedBase3 = Mockito.mock();
        mockedBase5 = Mockito.mock();
        mockedBase10 = Mockito.mock();

        mockedFunction = new Function(mockedSinus, mockedNaturalLogarithm, mockedCosine, mockedSecant, mockedTangent,
                mockedCotangent, mockedBase3, mockedBase5, mockedBase10);

        sinus = new Sinus();
        naturalLogarithm = new NaturalLogarithm();

        Cosine cosine = new Cosine(sinus);
        Secant secant = new Secant(cosine);
        Tangent tangent = new Tangent(sinus, cosine);
        Cotangent cotangent = new Cotangent(tangent);

        BaseLogarithm base3 = new BaseLogarithm(naturalLogarithm);
        base3.setBase(3);
        BaseLogarithm base5 = new BaseLogarithm(naturalLogarithm);
        base5.setBase(5);
        BaseLogarithm base10 = new BaseLogarithm(naturalLogarithm);
        base10.setBase(10);

        function = new Function(sinus, naturalLogarithm, cosine, secant, tangent, cotangent, base3, base5, base10);
    }

    @Test
    public void calculateTrigonometryExecutionFlow() {
        double x = -1;

        Mockito.when(mockedSinus.calculate(Mockito.eq(x))).thenReturn(-0.84147);
        Mockito.when(mockedCosine.calculate(Mockito.eq(x))).thenReturn(0.54030);
        Mockito.when(mockedTangent.calculate(Mockito.eq(x))).thenReturn(-1.55740);
        Mockito.when(mockedCotangent.calculate(Mockito.eq(x))).thenReturn(-0.64209);
        Mockito.when(mockedSecant.calculate(Mockito.eq(x))).thenReturn(1.85081);

        Assertions.assertEquals(4.92879, mockedFunction.calculate(x), 0.001);
    }

    @Test
    public void calculateLogarithmExecutionFlow() {
        double x = 2;

        Mockito.when(mockedNaturalLogarithm.calculate(Mockito.eq(x))).thenReturn(0.69314);
        Mockito.when(mockedBase3.calculate(Mockito.eq(x))).thenReturn(0.63092);
        Mockito.when(mockedBase5.calculate(Mockito.eq(x))).thenReturn(0.43067);
        Mockito.when(mockedBase10.calculate(Mockito.eq(x))).thenReturn(0.30102);

        Assertions.assertEquals(0.10134, mockedFunction.calculate(x), 0.001);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "-5.3, -0.65911",
            "-3.5, -1.87096",
            "-2.5, -1.76795",
            "-0.5, 1.98737"
    })
    public void calculateTrigonometryExecutionFlow(double x, double result) {
        Assertions.assertEquals(result, function.calculate(x), 0.001);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1, 0",
            "1.73208, 0.01454",
            "0.5, 0.00028"
    })
    public void calculateLogarithmExecutionFlow(double x, double result) {
        Assertions.assertEquals(result, function.calculate(x), 0.001);
    }

    @ParameterizedTest
    @ValueSource(doubles = {
            -Math.PI / 2, 3 * -Math.PI / 2, -Math.PI, 0
    })
    public void calculateZeroDivision(double x) {
        Assertions.assertThrows(ArithmeticException.class, () -> function.calculate(x));
    }

    @ParameterizedTest
    @ValueSource(doubles = {
            Double.NaN, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY,
    })
    public void calculateThrowsExceptionOnExceptionalX(double x) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> function.calculate(x));
    }

}
