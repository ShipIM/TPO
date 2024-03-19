package org.example.math.trigonometry;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.first.math.Sinus;
import org.example.math.api.PrecisionFunction;

@Setter
@RequiredArgsConstructor
public class Cosine extends PrecisionFunction {

    private final Sinus sinus;

    public double calculate(double x) {
        double sinValue = sinus.calculate(x);

        return round(Math.sqrt(1 - Math.pow(sinValue, 2)));
    }

}
