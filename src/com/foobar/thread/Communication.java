package com.foobar.thread;

public class Communication {

	public static void main(String[] args) {
		testCommunication();
	}
	
	public static void testCommunication() {
		Printer printer = new Printer();
		Thread t1 = new Thread(printer);
		t1.setName("甲");
		Thread t2 = new Thread(printer);
		t2.setName("乙");
		t1.start();	
		t2.start();	
	}
}


class Printer implements Runnable {

	public int number = 0;
	
	@Override
	public void run() {
		while (true) {
			synchronized (this) {
				//
				notify();
				
				//
				if (number++ < 100) {
					try {Thread.sleep(10);} catch (Exception e) { e.printStackTrace(); }
					System.out.println(Thread.currentThread().getName() + ": "  + number);
				} else {
					break;
				}
				
				// 
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}