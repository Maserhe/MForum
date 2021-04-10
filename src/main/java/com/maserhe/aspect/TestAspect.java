package com.maserhe.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * 描述:
 * 测试切面编程
 *
 * @author Maserhe
 * @create 2021-04-09 19:43
 */
public class TestAspect {

    @Pointcut("execution(* com.maserhe.service.*.*(..))") // 所有的 service 所有的方法。
    public void pointcut(){}

    @Before("pointcut()")
    public void before() {
        System.out.println("这里是before");
    }

    @After("pointcut()")
    public void after() {
        System.out.println("这里是after");
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("around before");
        Object proceed = joinPoint.proceed();
        System.out.println("around after");
        return proceed;
    }
}
