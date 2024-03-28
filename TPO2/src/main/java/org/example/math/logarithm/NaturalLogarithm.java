package org.example.math.logarithm;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.first.api.Calculable;

@AllArgsConstructor
@NoArgsConstructor
@Setter
public class NaturalLogarithm implements Calculable {

    private int n = 50;
    private int precision = 5;

    public double calculate(double x) {
        if (Double.isNaN(x) || Double.isInfinite(x) || x <= 0) {
            throw new IllegalArgumentException("x must be a positive number");
        } else if (n <= 0) {
            throw new IllegalArgumentException("n must be positive number");
        }

        double result = 0, append;
        double variable = (x - 1) / (x + 1), step, difference = Math.pow(0.1, precision);

        for (int i = 1; i < n; i++) {
            step = (2 * i) - 1;

            append = Math.pow(variable, step) / step;
            result += append;
            if (Math.abs(append) < difference) {
                return result * 2;
            }
        }

        return result * 2;
    }

}
