package org.example.math.trigonometry;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.math.api.PrecisionFunction;

@Setter
@RequiredArgsConstructor
public class Cotangent extends PrecisionFunction {

    private final Tangent tangent;

    public double calculate(double x) {
        return round(1 / tangent.calculate(x));
    }

}
