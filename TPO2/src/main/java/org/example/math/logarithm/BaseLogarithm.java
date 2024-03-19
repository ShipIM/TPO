package org.example.math.logarithm;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.math.api.PrecisionFunction;

@Setter
@RequiredArgsConstructor
public class BaseLogarithm extends PrecisionFunction {

    private final NaturalLogarithm naturalLogarithm;

    public double calculate(int base, double x) {
        return round(naturalLogarithm.calculate(x) / naturalLogarithm.calculate(base));
    }

}
