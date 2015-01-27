package com.foobar.reflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class Proxy3Class {
    public static void main(String[] args) {
        Service target = new ServiceImp();
        Service proxy = new ServiceProxy(target).getServiceProxy();
        
        System.out.println(((ServiceImp) target).getPersons());
        proxy.add(new Person(1003, "Matt"));
        System.out.println(((ServiceImp) target).getPersons());
        
        System.out.println(((ServiceImp) target).getPersons());
        proxy.update(new Person(1003, "Mary"));
        System.out.println(((ServiceImp) target).getPersons());
        
        System.out.println(((ServiceImp) target).getPersons());
        proxy.delete(1002);
        System.out.println(((ServiceImp) target).getPersons());
        
        System.out.println(((ServiceImp) target).getPersons());
        proxy.delete(1001);
        System.out.println(((ServiceImp) target).getPersons());
    }
}

class ServiceProxy {
    private Service target;
    
    public ServiceProxy(Service target) {
        this.target = target;
    }

    public Service getServiceProxy() {
        ClassLoader loader = target.getClass().getClassLoader();
        Class<?>[] interfaces = target.getClass().getInterfaces();
        Service proxy = (Service) Proxy.newProxyInstance(
                loader,
                interfaces,
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args)
                            throws Throwable {
                        System.out.println("Start action");
                        try {
                            Object returnValue = method.invoke(target, args);
                            System.out.println("Commit action");
                            return returnValue;
                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("Rollback action");
                        }
                        return null;
                    }
                });
        
        return proxy;
    }
    
}


class ServiceImp implements Service {

    private Map<Integer, Person> persons = new HashMap<Integer, Person>();

    public Map<Integer, Person> getPersons() {
        return persons;
    }

    public ServiceImp() {
        persons.put(1001, new Person(1001, "Lily"));
        persons.put(1002, new Person(1002, "Mike"));
    }

    @Override
    public void add(Person p) {
        persons.put(p.getId(), p);
    }

    @Override
    public void delete(int id) {
        if (1001 == id) {
            throw new RuntimeException("id 为 1001 的用户不能删除");
        }

        persons.remove(id);
    }

    @Override
    public void update(Person p) {
        persons.put(p.getId(), p);
    }
}

interface Service {
    void add(Person p);
    void delete(int id);
    void update(Person p);
}

class Person {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person [id=" + id + ", name=" + name + "]";
    }
}