package com.foobar.thread;

public class ProducerCustomerIssue {

	public static void main(String[] args) {
		testProducerCustomerIssue();
	}
	
	public static void testProducerCustomerIssue () {
		Product product = new Product();
		
		Producer producer = new Producer(product);
		Customer customer = new Customer(product);
		Thread t1 = new Thread(producer);
		Thread t2 = new Thread(customer);
		t1.setName("生产者1");
		t2.setName("消费者1");
		t1.start();
		t2.start();
		Thread t3 = new Thread(producer);
		Thread t4 = new Thread(customer);
		t3.setName("生产者2");
		t4.setName("消费者2");
		t3.start();
		t4.start();
		
		
	}
}


class Product {
	private int productCount;

	public synchronized void addProduct() {
		if (productCount >= 20) {
			try { wait(); } catch (InterruptedException e) { e.printStackTrace(); }
		} else {
			productCount++;
			System.out.println(Thread.currentThread().getName() + ": " + "生产了第" + productCount + "个产品");
			notifyAll();
		}
	}
	
	public synchronized void removeProduct() {
		if (productCount <= 0) {
			try { wait(); } catch (InterruptedException e) { e.printStackTrace(); }
		} else {
			productCount--;
			System.out.println(Thread.currentThread().getName() + ": " + "消费了第" + productCount + "个产品");
			notifyAll();
		}
	}
}

class Producer implements Runnable {
	Product product;
	
	public Producer(Product product) {
		this.product = product;
	}
	
	@Override
	public void run() {
		while (true) {
			product.addProduct();
		}
	}
}

class Customer implements Runnable {
	Product product;
	
	public Customer(Product product) {
		this.product = product;
	}
	@Override
	public void run() {
		while (true) {
			product.removeProduct();
		}
	}
}
