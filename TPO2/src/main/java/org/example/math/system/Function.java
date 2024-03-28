package org.example.math.system;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.first.api.Calculable;
import org.example.first.math.Sinus;
import org.example.math.logarithm.BaseLogarithm;
import org.example.math.logarithm.NaturalLogarithm;
import org.example.math.trigonometry.Cosine;
import org.example.math.trigonometry.Cotangent;
import org.example.math.trigonometry.Secant;
import org.example.math.trigonometry.Tangent;

@RequiredArgsConstructor
@Setter
public class Function implements Calculable {

    private final Sinus sinus;
    private final NaturalLogarithm naturalLogarithm;

    private final Cosine cosine;
    private final Secant secant;
    private final Tangent tangent;
    private final Cotangent cotangent;

    private final BaseLogarithm base3;
    private final BaseLogarithm base5;
    private final BaseLogarithm base10;

    public double calculate(double x) {
        if (x <= 0) {
            return (sinus.calculate(x) - tangent.calculate(x) - secant.calculate(x) +
                    cosine.calculate(x) * tangent.calculate(x)) / cotangent.calculate(x) + secant.calculate(x);
        } else {
            return Math.pow(Math.pow(naturalLogarithm.calculate(x) * naturalLogarithm.calculate(x) +
                    base10.calculate(x), 2), 2) * base5.calculate(x) * base3.calculate(x);
        }
    }

}
