package org.example.api.writer;

import org.example.first.api.Calculable;

@FunctionalInterface
public interface CalculableWriter {

    String write(String filename, Calculable calculable);

}
