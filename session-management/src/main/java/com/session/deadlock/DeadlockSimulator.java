package com.session.deadlock;

public class DeadlockSimulator {
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    public void method1() {
        synchronizedLocks(lock1, lock2, () ->
                System.out.println("Method1: Acquired lock1 and lock2"));
    }

    public void method2() {
        synchronizedLocks(lock1, lock2, () ->
                System.out.println("Method2: Acquired lock1 and lock2"));
    }

    private void synchronizedLocks(Object first, Object second, Runnable action) {
        synchronized (first) {
            synchronized (second) {
                action.run();
            }
        }
    }

    public static void main(String[] args) {
        DeadlockSimulator simulator = new DeadlockSimulator();
        new Thread(simulator::method1).start();
        new Thread(simulator::method2).start();
    }
}
