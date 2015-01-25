package com.foobar.java;

public class InnerClass {

	public InnerClass() {
	}
	
	class Inner {
		
	}
}

class Person {
	String name;
	int age;
	
	@SuppressWarnings("unused")
	private class Bird {
		
	}
	
	@SuppressWarnings("unused")
	public void show() {
		class Some {
			
		}
	}
}