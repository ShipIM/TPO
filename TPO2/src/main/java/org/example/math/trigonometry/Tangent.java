package org.example.math.trigonometry;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.first.math.Sinus;
import org.example.math.api.PrecisionFunction;

@Setter
@RequiredArgsConstructor
public class Tangent extends PrecisionFunction {

    private final Sinus sinus;
    private final Cosine cosine;

    @Override
    public double calculate(double x) {
        return round(sinus.calculate(x) / cosine.calculate(x));
    }

}
