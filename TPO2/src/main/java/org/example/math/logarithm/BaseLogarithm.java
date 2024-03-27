package org.example.math.logarithm;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.api.math.PrecisionFunction;

@Setter
@RequiredArgsConstructor
public class BaseLogarithm extends PrecisionFunction {

    private final NaturalLogarithm naturalLogarithm;
    private final Integer base;

    public double calculate(double x) {
        return round(naturalLogarithm.calculate(x) / naturalLogarithm.calculate(base));
    }

}
