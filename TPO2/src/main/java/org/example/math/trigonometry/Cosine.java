package org.example.math.trigonometry;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.first.api.Calculable;
import org.example.first.math.Sinus;

@RequiredArgsConstructor
@Setter
public class Cosine implements Calculable {

    private final Sinus sinus;
    private static final double TOLERANCE = 0.001;

    public double calculate(double x) {
        double sinValue = sinus.calculate(x), remains = Math.abs(x % (Math.PI * 2)), result;
        int eps = remains > (Math.PI / 2) && remains < (3 * Math.PI / 2) ? -1 : 1;

        if (Math.abs(result = eps * Math.sqrt(1 - Math.pow(sinValue, 2))) < TOLERANCE) {
            return 0;
        }

        return result;
    }

}
