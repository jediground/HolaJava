package com.foobar.thread;

public class SellTicketIssue {

    public static void main(String[] args) throws Exception {
        // testThread1();
        // testThread2();
        testThread3();
    }

    public static void testThread1() {
        Window w1 = new Window();
        w1.setName("W1");

        Window w2 = new Window();
        w2.setName("W2");

        Window w3 = new Window();
        w3.setName("W3");

        w2.start();
        w1.start();
        w3.start();
    }

    public static void testThread2() {
        Window1 w1 = new Window1();

        Thread t1 = new Thread(w1);
        t1.setName("W1");

        Thread t2 = new Thread(w1);
        t2.setName("W2");

        Thread t3 = new Thread(w1);
        t3.setName("W3");

        t1.start();
        t2.start();
        t3.start();
    }

    public static void testThread3() {
        Window2 w1 = new Window2();

        Thread t1 = new Thread(w1);
        t1.setName("W1");

        Thread t2 = new Thread(w1);
        t2.setName("W2");

        Thread t3 = new Thread(w1);
        t3.setName("W3");

        t2.start();
        t1.start();
        t3.start();

        System.out.println("OK");
    }
}

class Window extends Thread {
    public static int ticketRemains = 100;

    @Override
    public void run() {
        super.run();
        for (; ticketRemains-- > 1;) {
            System.out.println(Thread.currentThread().getName() + " :"
                    + "Sell ticket remains: " + ticketRemains);
        }
    }
}

class Window1 implements Runnable {
    int ticketRemains = 1000;

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                if (ticketRemains > 0) {
                    System.out.println(Thread.currentThread().getName() + " :"
                            + "Sell ticket remains: " + ticketRemains--);
                } else {
                    break;
                }
            }
        }
    }
}

class Window2 implements Runnable {
    int ticketRemains = 1000;

    @Override
    public void run() {
        while (true) {
            boolean flag = printRemains();
            if (!flag) {
                break;
            }
        }
    }

    public synchronized boolean printRemains() {
        if (ticketRemains > 0) {
            System.out.println(Thread.currentThread().getName() + " :"
                    + "Sell ticket remains: " + ticketRemains--);
            return true;
        }

        return false;
    }
}