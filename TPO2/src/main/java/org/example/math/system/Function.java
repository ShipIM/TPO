package org.example.math.system;

import lombok.Setter;
import org.example.first.math.Sinus;
import org.example.api.math.PrecisionFunction;
import org.example.math.logarithm.BaseLogarithm;
import org.example.math.logarithm.NaturalLogarithm;
import org.example.math.trigonometry.Cosine;
import org.example.math.trigonometry.Cotangent;
import org.example.math.trigonometry.Secant;
import org.example.math.trigonometry.Tangent;

@Setter
public class Function extends PrecisionFunction {

    private final Sinus sinus;
    private final NaturalLogarithm naturalLogarithm;

    private final Cosine cosine;
    private final Secant secant;
    private final Tangent tangent;
    private final Cotangent cotangent;

    private final BaseLogarithm base3;
    private final BaseLogarithm base5;
    private final BaseLogarithm base10;

    public Function(Sinus sinus, NaturalLogarithm naturalLogarithm) {
        this.sinus = sinus;
        this.naturalLogarithm = naturalLogarithm;

        cosine = new Cosine(sinus);
        secant = new Secant(cosine);
        tangent = new Tangent(sinus, cosine);
        cotangent = new Cotangent(tangent);

        base3 = new BaseLogarithm(naturalLogarithm, 3);
        base5 = new BaseLogarithm(naturalLogarithm, 5);
        base10 = new BaseLogarithm(naturalLogarithm, 10);
    }

    public double calculate(double x) {
        if (x <= 0) {
            return round(((((sinus.calculate(x) - tangent.calculate(x)) - secant.calculate(x)) +
                    (cosine.calculate(x) * tangent.calculate(x))) / cotangent.calculate(x)) + secant.calculate(x));
        } else {
            return round(Math.pow(Math.pow((((naturalLogarithm.calculate(x)) * naturalLogarithm.calculate(x)) +
                    base10.calculate(x)), 2), 2) * (base5.calculate(x))
                    * base3.calculate(x));
        }
    }

}
