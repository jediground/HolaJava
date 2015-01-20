package com.foobar.java;

import org.junit.Test;

public class SingletonClass {

	public SingletonClass() {
	
	}

	@Test
	public void testSingleton() {
		HungerSingleton singleton = HungerSingleton.getInstance();
		System.out.println(singleton);
	}
}

// 恶汉式
class HungerSingleton {
	
	// 1. 私有化构造器
	private HungerSingleton() {}
	
	// 2. 创建一个实例作为类变量
	private static final HungerSingleton sharedInstance = new HungerSingleton();
	
	// 3. 提供公共类方法访问该实例
	public static HungerSingleton getInstance() {
		return sharedInstance;
	}
}

// 懒汉式
class LazySingleton {
	
	// 1. 私有化构造器
	private LazySingleton() {}
	
	// 2. 私有化实例
	private static volatile LazySingleton sharedInstance;
	
	// 3. 提供公共类方法访问该实例
	public static LazySingleton getInstance() {
		if (null == sharedInstance) {
			synchronized(LazySingleton.class) {
				if (null == sharedInstance) {
					return new LazySingleton();		
				}
			}
		}
		return sharedInstance;
	}
}

// <<Effective Java>> preferred
class LazySingletonPrefreferred {
	
	private LazySingletonPrefreferred() {}
	
	private static class LazySingletonHoler {
		private static final LazySingletonPrefreferred sharedIntance = new LazySingletonPrefreferred();
	}
	
	public LazySingletonPrefreferred getInstance() {
		return LazySingletonHoler.sharedIntance;
	}
}




