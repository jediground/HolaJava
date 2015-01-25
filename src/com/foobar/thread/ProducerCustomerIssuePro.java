package com.foobar.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerCustomerIssuePro {

	public static void main(String[] args) {
		Resource r = new Resource();

		Producer1 pro = new Producer1(r);
		Consumer1 con = new Consumer1(r);

		Thread t1 = new Thread(pro);
		Thread t2 = new Thread(pro);
		t1.setName("生产者1");
		t2.setName("生产者2");
		Thread t3 = new Thread(con);
		Thread t4 = new Thread(con);
		t3.setName("消费者1");
		t4.setName("消费者2");

		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}
}

class Resource {
	private int count = 1;
	// t1 t2
	private Lock lock = new ReentrantLock();

	private Condition condition_pro = lock.newCondition();
	private Condition condition_con = lock.newCondition();

	public void addProduct() {
		lock.lock();
		if (count >= 20) {
			try { condition_pro.await(); } catch (InterruptedException e) { e.printStackTrace(); }
		} else {
			count++;
			System.out.println(Thread.currentThread().getName() + ": " + "生产了第" + count + "个产品");
			condition_con.signal();
		}
		lock.unlock();
	}
	
	public void removeProduct() {
		lock.lock();
		if (count <= 0) {
			try { condition_con.await(); } catch (InterruptedException e) { e.printStackTrace(); }
		} else {
			count--;
			System.out.println(Thread.currentThread().getName() + ": " + "消费了第" + count + "个产品");
			condition_pro.signal();
		}
		lock.unlock();
	}
}

class Producer1 implements Runnable {
	private Resource res;

	Producer1(Resource res) {
		this.res = res;
	}

	public void run() {
		while (true) {
			res.addProduct();
		}
	}
}

class Consumer1 implements Runnable {
	private Resource res;

	Consumer1(Resource res) {
		this.res = res;
	}

	public void run() {
		while (true) {
			res.removeProduct();
		}
	}
}
