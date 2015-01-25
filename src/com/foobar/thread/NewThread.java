package com.foobar.thread;

public class NewThread {

	public static void main(String[] args) {
		
		new Thread() {
			@Override
			public void run() {
				for (int i= 0; i < 100; i++) {
					System.out.println(Thread.currentThread().getName() + ":" + i);
				}
			}
		}.start();
		
		new Thread(
			new Runnable() {
				@Override
				public void run() {
					for (int i= 0; i < 100; i++) {
						System.out.println(Thread.currentThread().getName() + ":" + i);
					}
				}
			}
		).start();
		
		for (int i= 0; i < 100; i++) {
			System.out.println(Thread.currentThread().getName() + ":" + i);
		}
		
	}
	
	public NewThread() {
	}

	
}
