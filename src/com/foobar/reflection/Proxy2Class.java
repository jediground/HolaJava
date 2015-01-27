package com.foobar.reflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

import org.junit.Test;

public class Proxy2Class {
    
    @Test
    public void testCount()
    {
        Counter ci = new CounterLow();
        ci.add(2, 4);
        ci.dem(4, 2);
        ci.div(6, 2);
        ci.mul(1, 4);
    }
    
    @Test
    public void testProxy2() {
        CounterHigh high = new CounterHigh();
        Counter counter = (new CounterInvocationHandler()).bind(high);
        System.out.println(counter.add(10 ,2));
        counter.mul(12, 3);
        counter.div(30, 3);
    }
    
    @Test
    public void testProxy() {
        CounterLow low = new CounterLow();
        
        ClassLoader loader = CounterLow.class.getClassLoader();
        Class<?>[] interfaces = CounterLow.class.getInterfaces();
        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args)
                    throws Throwable {
                Object returnValue = method.invoke(low, args);
                return returnValue;
            }
        };
        
        Counter counter = (Counter)Proxy.newProxyInstance(loader, interfaces, handler);
        System.out.println(counter.add(10, 2));
        counter.mul(12, 3);
        counter.div(30, 3);
    }
}

class CounterInvocationHandler implements InvocationHandler {
    private Counter obj; 
    public Counter bind(Counter obj) {
        this.obj = obj;
        ClassLoader loader = obj.getClass().getClassLoader();
        Class<?>[] interfaces = obj.getClass().getInterfaces();
        return (Counter)Proxy.newProxyInstance(loader, interfaces, this);
    }
    
    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        System.out.println("the " + method.getName() + " method begin with "+Arrays.asList(args));
        Object returnValue = method.invoke(obj, args);
        System.out.println("the "+ method.getName()+ " method ends with " + returnValue);
        return returnValue;
    }
    
}

interface Counter {
    public int add(int i, int j);
    public int dem(int i, int j);
    public void mul(int i, int j);
    public void div(int i, int j);
}

class CounterLow implements Counter {
    @Override
    public int add(int i, int j) {
        System.out.println("the add method begin with " + i + ", " + j);
        int returnValue = i + j;
        System.out.println("the add method ends with " + returnValue);
        return returnValue;
    }

    @Override
    public int dem(int i, int j) {
        System.out.println("the dem method begin with " + i + ", " + j);
        int returnValue = i + j;
        System.out.println("the dem method ends with " + returnValue);
        return returnValue;
    }

    @Override
    public void mul(int i, int j) {
        System.out.println("the mul method begin with " + i + ", " + j);
        int returnValue = i * j;
        System.out.println("result = " + returnValue);
        System.out.println("the mul method ends with " + returnValue);
    }

    @Override
    public void div(int i, int j) {
        System.out.println("the div method begin with " + i + ", " + j);
        int returnValue = i / j;
        System.out.println("result = " + returnValue);
        System.out.println("the div method ends with " + returnValue);
    }
}

class CounterHigh implements Counter {
    @Override
    public int add(int i, int j) {
        int returnValue = i + j;
        return returnValue;
    }

    @Override
    public int dem(int i, int j) {
        int returnValue = i + j;
        return returnValue;
    }

    @Override
    public void mul(int i, int j) {
        int returnValue = i * j;
        System.out.println("result=" + returnValue);
    }

    @Override
    public void div(int i, int j) {
        int returnValue = i / j;
        System.out.println("result=" + returnValue);
    }
}