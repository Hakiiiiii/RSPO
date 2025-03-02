package org.example;

public class Main {
    public static void main(String[] args) {
        int numberOfThreads = 5;
        int calculationLength = 100;

        for (int i = 0; i < numberOfThreads; i++) {
            Thread thread = new Thread(new MultiThreadedCalculation(i + 1, calculationLength));
            thread.start();
        }
    }
}