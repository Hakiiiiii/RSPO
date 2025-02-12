package org.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class FileFilter {
    private List<String> inputFiles = new ArrayList<>();
    private String outputPath = ".";
    private String prefix = "";
    private boolean appendMode = false;
    private boolean fullStatistics = false;

    private Statistics intStats = new Statistics();
    private Statistics floatStats = new Statistics();
    private Statistics stringStats = new Statistics();

    public void parseArguments(String[] args) {
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-o":
                    outputPath = args[++i];
                    break;
                case "-p":
                    prefix = args[++i];
                    break;
                case "-a":
                    appendMode = true;
                    break;
                case "-s":
                    fullStatistics = false;
                    break;
                case "-f":
                    fullStatistics = true;
                    break;
                default:
                    inputFiles.add(args[i]);
            }
        }
    }

    public void processFiles() throws IOException {
        for (String inputFile : inputFiles) {
            try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    processLine(line);
                }
            }
        }
        writeResults();
    }

    private void processLine(String line) {
        try {
            int intValue = Integer.parseInt(line);
            intStats.addValue(intValue);
            return;
        } catch (NumberFormatException e) {

        }

        try {
            double floatValue = Double.parseDouble(line);
            floatStats.addValue(floatValue);
            return;
        } catch (NumberFormatException e) {

        }

        stringStats.addValue(line);
    }

    private void writeResults() throws IOException {
        writeFile("integers.txt", intStats.getValues());
        writeFile("floats.txt", floatStats.getValues());
        writeFile("strings.txt", stringStats.getValues());
    }

    private void writeFile(String fileName, List<?> values) throws IOException {
        if (values.isEmpty()) return;


        Path outputDir = Paths.get(outputPath);
        if (!Files.exists(outputDir)) {
            Files.createDirectories(outputDir);
        }

        Path filePath = Paths.get(outputPath, prefix + fileName);
        StandardOpenOption option = appendMode ? StandardOpenOption.APPEND : StandardOpenOption.TRUNCATE_EXISTING;

        try (BufferedWriter writer = Files.newBufferedWriter(filePath, StandardOpenOption.CREATE, option)) {
            for (Object value : values) {
                writer.write(value.toString());
                writer.newLine();
            }
        }
    }

    public void printStatistics() {
        System.out.println("Integers: " + intStats.getSummary(fullStatistics));
        System.out.println("Floats: " + floatStats.getSummary(fullStatistics));
        System.out.println("Strings: " + stringStats.getSummary(fullStatistics));
    }
}