package org.example.math.system;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.first.math.Sinus;
import org.example.math.api.PrecisionFunction;
import org.example.math.logarithm.BaseLogarithm;
import org.example.math.logarithm.NaturalLogarithm;
import org.example.math.trigonometry.Cosine;
import org.example.math.trigonometry.Cotangent;
import org.example.math.trigonometry.Secant;
import org.example.math.trigonometry.Tangent;

@Setter
@RequiredArgsConstructor
public class Function extends PrecisionFunction {

    private final Sinus sinus;
    private final Cosine cosine;
    private final Tangent tangent;
    private final Cotangent cotangent;
    private final Secant secant;

    private final BaseLogarithm baseLogarithm;
    private final NaturalLogarithm naturalLogarithm;

    public double calculate(double x) {
        if (x <= 0) {
            return ((((sinus.calculate(x) - tangent.calculate(x)) - secant.calculate(x)) +
                    (cosine.calculate(x) * tangent.calculate(x))) / cotangent.calculate(x)) + secant.calculate(x);
        } else {
            return Math.pow(Math.pow((((naturalLogarithm.calculate(x)) * naturalLogarithm.calculate(x)) +
                    baseLogarithm.calculate(10, x)), 2), 2) *
                    (baseLogarithm.calculate(5, x)) * baseLogarithm.calculate(3, x);
        }
    }

}
