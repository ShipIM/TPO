package org.example.first;

import org.example.first.math.Sinus;

public class FirstTask {

    public static void main(String[] args) {
        Sinus sinus = new Sinus(1, 1);

        double x = -Math.PI;

        System.out.println(sinus.calculate(x));
    }

}
