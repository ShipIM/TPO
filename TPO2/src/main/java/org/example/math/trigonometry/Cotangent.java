package org.example.math.trigonometry;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.first.api.Calculable;

@RequiredArgsConstructor
@Setter
public class Cotangent implements Calculable {

    private final Tangent tangent;
    private static final double TOLERANCE = 0.001;

    public double calculate(double x) {
        double tangentValue = tangent.calculate(x), result;
        if (Math.abs(tangentValue) < TOLERANCE || Double.isInfinite(result = 1 / tangentValue)) {
            throw new ArithmeticException("zero division cannot be performed");
        }

        return result;
    }

}
