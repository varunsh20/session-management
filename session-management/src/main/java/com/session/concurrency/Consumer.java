package com.session.concurrency;

public class Consumer extends Thread {
    private final LogProcessor processor;

    public Consumer(LogProcessor processor) {
        this.processor = processor;
    }

    @Override
    public void run() {
        try {
            while (true) {
                LogProcessor.LogTask task = processor.consumeLog();
                System.out.println("Consumed (priority " + task.getPriority() + "): " + task.getMessage());
                Thread.sleep(100); // simulate processing delay
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}