package org.example.math.logarithm;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.first.api.Calculable;

import java.util.Objects;

@RequiredArgsConstructor
@Setter
public class BaseLogarithm implements Calculable {

    private final NaturalLogarithm naturalLogarithm;
    private Integer base;

    public double calculate(double x) {
        if (Objects.isNull(base) || base <= 0 || base == 1) {
            throw new IllegalArgumentException("base must be positive number and not 1");
        }

        return naturalLogarithm.calculate(x) / naturalLogarithm.calculate(base);
    }

}
