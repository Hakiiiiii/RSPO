package org.example;

public class MultiThreadedCalculation implements Runnable {
    private final int threadNumber;
    private final int calculationLength;
    private long startTime;

    public MultiThreadedCalculation(int threadNumber, int calculationLength) {
        this.threadNumber = threadNumber;
        this.calculationLength = calculationLength;
    }

    @Override
    public void run() {
        startTime = System.currentTimeMillis();
        for (int i = 0; i < calculationLength; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread " + threadNumber + " was interrupted.");
                return;
            }
            printProgress(i + 1);
        }
        long endTime = System.currentTimeMillis();
        synchronized (System.out) {
            System.out.println("\nThread " + threadNumber + " (ID: " + Thread.currentThread().getId() + ") finished in " + (endTime - startTime) + " ms.");
        }
    }

    private void printProgress(int progress) {
        int percent = (int) ((double) progress / calculationLength * 100);
        StringBuilder progressBar = new StringBuilder("[");
        for (int i = 0; i < 100; i += 10) {
            if (i < percent) {
                progressBar.append("=");
            } else {
                progressBar.append(" ");
            }
        }
        progressBar.append("] " + percent + "%");

        synchronized (System.out) {

            System.out.print("\rThread " + threadNumber + " (ID: " + Thread.currentThread().getId() + ") " + progressBar.toString());
        }
    }
}