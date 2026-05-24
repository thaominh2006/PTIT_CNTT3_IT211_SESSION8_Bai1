package com.example.ex1.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerformanceAspect {
    @Around("execution(* com.example.ex1.service.ProductService.inspectInventory(..)) && args(username, role)")
    public Object trackInspectInventoryTime(
            ProceedingJoinPoint joinPoint, String username, String role
    ) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();
        System.out.println("inspectInventory executed in " + (end - start) + " ms");
        return result;
    }
}