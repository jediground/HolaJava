package com.foobar.reflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyClass {

    public static void main(String[] args) {
        RealSubject real = new RealSubject();
        MyInvocationHandler handler = new MyInvocationHandler();
        Object obj =  handler.blind(real);
        Subject sub = (Subject)obj;
        sub.action();
    }
}

// 动态代理
interface Subject {
    void action();
}

class RealSubject implements Subject {
    @Override
    public void action() {
        System.out.println("Runing proxy method....");
    }
}

class MyInvocationHandler implements InvocationHandler {

    Object obj;
    public Object blind(Object obj) {
        this.obj = obj;
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
    }
    
    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        return method.invoke(obj, args);
    }
}