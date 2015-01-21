package com.foobar.java;

public class TemplateClass {

	public TemplateClass() {
	}
	
	public static void main(String[] args) {
		new SubTemplate().spendTime();
	}

}

abstract class Template {
	abstract void code();
	
	public void spendTime() {
		long startTime = System.currentTimeMillis();
		code();
		long endTime = System.currentTimeMillis();
		System.out.println("Spend time :" + (endTime - startTime));
	}
	
}

class SubTemplate extends Template {
	public void code() {
		boolean flag = false;
		for (int i = 2; i < 100000; i++) {
			for (int j = 2; i < 100000; j++) {
				if  (0 == i % j ) {
					flag = true;
					break;
				}
			}
			if (!flag) {
				System.out.println(i);
			}
			flag = false;
		}
	}
}