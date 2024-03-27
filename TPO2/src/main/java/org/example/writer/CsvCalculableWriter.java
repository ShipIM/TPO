package org.example.writer;

import lombok.AllArgsConstructor;
import lombok.Setter;
import org.example.api.writer.CalculableWriter;
import org.example.exception.FileWriteException;
import org.example.first.api.Calculable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Locale;

@AllArgsConstructor
@Setter
public class CsvCalculableWriter implements CalculableWriter {

    private Float lowerBound;
    private Float upperBound;
    private Float step;

    public String write(String filename, Calculable calculable) {
        try {
            var path = Paths.get(filename);
            if (!Files.exists(path.getParent())) {
                Files.createDirectories(path.getParent());
            }
            if (!Files.exists(path)) {
                Files.createFile(path);
            }

            StringBuilder builder = new StringBuilder();

            for (float i = lowerBound; i <= upperBound; i += step) {
                try {
                    String append = String.format(Locale.US, "%.5f,%.5f\n", i, calculable.calculate(i));
                    builder.append(append);
                } catch (RuntimeException ignored) {
                }
            }

            Files.writeString(path, builder.toString());

            return filename;
        } catch (IOException e) {
            throw new FileWriteException("Unable to write CSV to file: " + filename);
        }
    }

}