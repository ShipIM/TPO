package org.example;

import org.example.first.math.Sinus;
import org.example.math.logarithm.BaseLogarithm;
import org.example.math.logarithm.NaturalLogarithm;
import org.example.math.system.Function;
import org.example.math.trigonometry.Cosine;
import org.example.math.trigonometry.Cotangent;
import org.example.math.trigonometry.Secant;
import org.example.math.trigonometry.Tangent;
import org.example.writer.CsvCalculableWriter;

public class SecondLab {

    public static void main(String[] args) {
        Sinus sinus = new Sinus();
        NaturalLogarithm naturalLogarithm = new NaturalLogarithm();

        Cosine cosine = new Cosine(sinus);
        Secant secant = new Secant(cosine);
        Tangent tangent = new Tangent(sinus, cosine);
        Cotangent cotangent = new Cotangent(tangent);

        BaseLogarithm base3 = new BaseLogarithm(naturalLogarithm);
        base3.setBase(3);
        BaseLogarithm base5 = new BaseLogarithm(naturalLogarithm);
        base5.setBase(5);
        BaseLogarithm base10 = new BaseLogarithm(naturalLogarithm);
        base10.setBase(10);

        Function function = new Function(sinus, naturalLogarithm, cosine, secant, tangent, cotangent,
                base3, base5, base10);

        CsvCalculableWriter writer = new CsvCalculableWriter(-2f, 2f, 0.01f);
        writer.write("csv/function.csv", sinus);
    }

}
