package org.example.math.trigonometry;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.first.api.Calculable;

@RequiredArgsConstructor
@Setter
public class Secant implements Calculable {

    private final Cosine cosine;
    private static final double TOLERANCE = 0.001;

    public double calculate(double x) {
        double cosineValue = cosine.calculate(x), result;
        if (Math.abs(cosineValue) < TOLERANCE || Double.isInfinite(result = 1 / cosineValue)) {
            throw new ArithmeticException("zero division cannot be performed");
        }

        return result;
    }

}
