package org.example.first.math;

public class Sinus {

    public double calculate(double x, int n) {

        if (Double.isNaN(x) || Double.isInfinite(x)) {
            throw new IllegalArgumentException("x must be a number");
        }

        if (n <= 0) {
            throw new IllegalArgumentException("n must be positive number");
        }

        double result = 0;
        x = period(x);

        int sign = 1;
        double degreeStep = x * x, variable = x;
        double step = 1, factorial = step;

        for (int i = 1; i < n; i++) {
            factorial *= (2 * i - 1) * (2 * i - 2 == 0 ? 1 : 2 * i - 2);
            if (Double.isInfinite(factorial)) {
                throw new ArithmeticException("too big n value");
            }

            result += sign * variable / factorial;

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
