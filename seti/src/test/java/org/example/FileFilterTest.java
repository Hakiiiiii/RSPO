package org.example;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FileFilterTest {
    @Test
    public void testFileFilter() throws IOException {
        // временные файлы
        Path inputFile = Files.createTempFile("input", ".txt");
        Files.write(inputFile, List.of("123", "45.67", "hello", "world"));

        FileFilter fileFilter = new FileFilter();
        fileFilter.parseArguments(new String[]{inputFile.toString()});
        fileFilter.processFiles();

        // проверка
        assertTrue(Files.exists(Path.of("integers.txt")));
        assertTrue(Files.exists(Path.of("floats.txt")));
        assertTrue(Files.exists(Path.of("strings.txt")));

        // очистка
        Files.deleteIfExists(inputFile);
        Files.deleteIfExists(Path.of("integers.txt"));
        Files.deleteIfExists(Path.of("floats.txt"));
        Files.deleteIfExists(Path.of("strings.txt"));
    }
}