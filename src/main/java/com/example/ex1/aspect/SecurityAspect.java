package com.example.ex1.aspect;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SecurityAspect {
    @Before("execution(* com.example.ex1.service.ProductService.deleteProduct(..)) " +
            "&& args(id, username, role)")
    public void checkAdminPermission(Long id, String username, String role) {
        if (!"ADMIN".equalsIgnoreCase(role)) {
            throw new SecurityException("Chỉ ADMIN mới được phép xóa sản phẩm!");
        }
    }
}