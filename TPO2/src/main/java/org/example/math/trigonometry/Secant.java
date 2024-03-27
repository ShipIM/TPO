package org.example.math.trigonometry;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.api.math.PrecisionFunction;

@Setter
@RequiredArgsConstructor
public class Secant extends PrecisionFunction {

    private final Cosine cosine;

    public double calculate(double x) {
        return round(1 / cosine.calculate(x));
    }

}
