package org.example;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        try {
            FileFilter fileFilter = new FileFilter();
            fileFilter.parseArguments(args);
            fileFilter.processFiles();
            fileFilter.printStatistics();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}