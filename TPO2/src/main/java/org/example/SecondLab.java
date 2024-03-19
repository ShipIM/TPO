package org.example;

import org.example.first.math.Sinus;
import org.example.math.logarithm.BaseLogarithm;
import org.example.math.logarithm.NaturalLogarithm;
import org.example.math.system.Function;
import org.example.math.trigonometry.Cosine;
import org.example.math.trigonometry.Cotangent;
import org.example.math.trigonometry.Secant;
import org.example.math.trigonometry.Tangent;

public class SecondLab {

    public static void main(String[] args) {
        Sinus sinus = new Sinus();
        Cosine cosine = new Cosine(sinus);
        Tangent tangent = new Tangent(sinus, cosine);
        Cotangent cotangent = new Cotangent(tangent);
        Secant secant = new Secant(cosine);

        NaturalLogarithm naturalLogarithm = new NaturalLogarithm();
        BaseLogarithm baseLogarithm = new BaseLogarithm(naturalLogarithm);

        Function function = new Function(sinus, cosine, tangent, cotangent, secant, baseLogarithm, naturalLogarithm);

        System.out.println(function.calculate(2));
    }

}
