package com.foobar.java;

import org.junit.Test;

public class InterfaceClass {

	public InterfaceClass() {
	}

	public static void main(String[] args) {

	}
	
	@Test
	public void testPolymorphic() {
		new Computer().doWork(new Disk());
		new Computer().doWork(new Printer());
		new Computer().doWork(new USB() {
			@Override
			public void start() {
				System.out.println("Some other usb device begin runing...");
			}
			@Override
			public void stop() {
				System.out.println("Some other usb device end runing...");
			}			
		});
	}
}

interface InterA {

	// 常量都是 public static final
	public static final int I = 12;
	public static final boolean FLAG = false;
	
	// 抽象方法都是 public abstract
	public abstract void method();
	void anotherMethod();
}

interface InterB {
	void interBMethod();
}

interface InterC {
	void interCMethod();
}

class Cls implements InterA {

	public void method() {
	}

	public void anotherMethod() {
	}
	
}

class ClsC extends Cls implements InterB, InterC {

	public void interCMethod() {
	}

	public void interBMethod() {
	}
}

// 多态

interface USB {
	void start();
	void stop();
}

class Printer implements USB {

	@Override
	public void start() {
		System.out.println("Printer begin working");
	}

	@Override
	public void stop() {
		System.out.println("Printer end working");		
	}
}

class Disk implements USB {

	@Override
	public void start() {
		System.out.println("Disk begin working");
	}

	@Override
	public void stop() {
		System.out.println("Disk end working");		
	}
	
}

class Computer {
	public void doWork(USB usb) {
		usb.start();
		System.out.println("Working....");
		usb.stop();
	}
}