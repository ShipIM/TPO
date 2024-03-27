package org.example.first.math;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.first.api.Calculable;

@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Sinus implements Calculable {

    private int n = 50;
    private int precision = 5;

    public double calculate(double x) {
        if (Double.isNaN(x) || Double.isInfinite(x)) {
            throw new IllegalArgumentException("x must be a number");
        }

        if (n <= 0) {
            throw new IllegalArgumentException("n must be positive number");
        }

        double result = 0, append;
        x = period(x);

        int sign = 1;
        double degreeStep = x * x, variable = x, difference = Math.pow(0.1, precision);
        double step = 1, factorial = step;

        for (int i = 1; i < n; i++) {
            factorial *= (2 * i - 1) * (2 * i - 2 == 0 ? 1 : 2 * i - 2);
            if (Double.isInfinite(factorial)) {
                throw new ArithmeticException("too big n value");
            }

            append = sign * variable / factorial;
            result += append;
            if (Math.abs(append) < difference) {
                return result;
            }

            variable *= degreeStep;
            sign *= -1;
        }

        return result;
    }

    private double period(double x) {
        if (x >= 0) {
            while (x > Math.PI * 2) {
                x -= Math.PI * 2;
            }
        } else {
            while (x < Math.PI) {
                x += Math.PI * 2;
            }
        }

        return x;
    }

}
