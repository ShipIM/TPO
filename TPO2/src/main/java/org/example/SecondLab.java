package org.example;

import org.example.first.math.Sinus;
import org.example.math.logarithm.NaturalLogarithm;
import org.example.math.system.Function;
import org.example.writer.CsvCalculableWriter;

public class SecondLab {

    public static void main(String[] args) {
        Sinus sinus = new Sinus();
        NaturalLogarithm naturalLogarithm = new NaturalLogarithm();

        Function function = new Function(sinus, naturalLogarithm);

        CsvCalculableWriter writer = new CsvCalculableWriter(-2f, 2f, 0.01f);
        writer.write("csv/sinus.csv", sinus);
    }

}
