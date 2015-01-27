package com.foobar.reflection;

import org.junit.Test;

public class ReflectionClass {

    @Test
    public void testClassLoader() {
        ClassLoader loader1 = ClassLoader.getSystemClassLoader();
        System.out.println(loader1);
        
//        ClassLoader
        
    }
    
    @Test
    public void testGetClass() throws ClassNotFoundException {
        // 1
        Class<GetClass> clazz1 =  GetClass.class;
        System.out.println(clazz1.getName());
        
        // 2
        GetClass instance = new GetClass();
        Class<? extends GetClass> clazz2 = instance.getClass();
        System.out.println(clazz2.getName());
        
        // 3 
        String className = "com.foobar.reflection.GetClass";
        Class<?> clazz3 = Class.forName(className);
        System.out.println(clazz3.getName());
        
        // 4
        ClassLoader loader = getClass().getClassLoader();
        Class<?> clazz4 = loader.loadClass(className);
        System.out.println(clazz4.getName());
        
    } 
    
}


class GetClass {
    public GetClass() {}
}