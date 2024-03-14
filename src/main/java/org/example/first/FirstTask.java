package org.example.first;

import org.example.first.math.Sinus;

public class FirstTask {

    public static void main(String[] args) {
        Sinus sinus = new Sinus();

        double x = 50;
        int n = 100;

        System.out.println(sinus.calculate(x, n));
    }

}
