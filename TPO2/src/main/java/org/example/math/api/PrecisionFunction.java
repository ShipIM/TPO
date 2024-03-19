package org.example.math.api;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@AllArgsConstructor
@NoArgsConstructor
@Setter
public abstract class PrecisionFunction {

    private int precision = 5;

    public double round(double value) {
        if (precision < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(precision, RoundingMode.HALF_EVEN);

        return bd.doubleValue();
    }

}
