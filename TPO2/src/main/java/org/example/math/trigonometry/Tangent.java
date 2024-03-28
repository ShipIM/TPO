package org.example.math.trigonometry;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.first.api.Calculable;
import org.example.first.math.Sinus;

@RequiredArgsConstructor
@Setter
public class Tangent implements Calculable {

    private final Sinus sinus;
    private final Cosine cosine;
    private static final double TOLERANCE = 0.001;

    public double calculate(double x) {
        double cosineValue = cosine.calculate(x), result;
        if (Math.abs(cosineValue) < TOLERANCE || Double.isInfinite(result = sinus.calculate(x) / cosineValue)) {
            throw new ArithmeticException("zero division cannot be performed");
        }

        return result;
    }

}
