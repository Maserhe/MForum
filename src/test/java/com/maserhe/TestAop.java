package com.maserhe;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 描述:
 *  测试 AOP
 * @author Maserhe
 * @create 2021-04-09 18:26
 */
public class TestAop {

    public static void main(String[] args) {

        Person person = new Person();
        InvocationHandler handler = new Handler(person);
        Person o = (Person) Proxy.newProxyInstance(handler.getClass().getClassLoader(), Person.class.getInterfaces(), handler);
        o.hello();

    }
}

class Person {
    public void hello() {
        System.out.println("hello");
    }
}

class Handler implements InvocationHandler{

    private Object subject;

    public Handler(Object proxy) {
        this.subject = proxy;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("dsfasdfasdfasd");
        Object invoke = method.invoke(subject, args);
        System.out.println("fadsfasdfasdfasdf");
        return invoke;
    }
}