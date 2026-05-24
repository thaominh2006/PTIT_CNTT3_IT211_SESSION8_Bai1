package com.example.ex1.aspect;

import com.example.ex1.entity.InventoryLog;
import com.example.ex1.repository.InventoryLogRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
@RequiredArgsConstructor
public class ActivityLoggingAspect {
    private final InventoryLogRepository inventoryLogRepository;
    @AfterReturning("execution(* com.example.ex1.service.ProductService.stockIn(..)) && args(sku, quantity, username, role)")
    public void logStockIn(String sku, Integer quantity, String username, String role) {
        saveLog(username, "STOCK_IN", sku, quantity);
    }
    @AfterReturning("execution(* com.example.ex1.service.ProductService.stockOut(..)) && args(sku, quantity, username, role)")
    public void logStockOut(String sku, Integer quantity, String username, String role) {
        saveLog(username, "STOCK_OUT", sku, quantity);
    }
    private void saveLog(String username, String action, String sku, Integer quantity) {
        String detail = LocalDateTime.now()
                + " - User: " + username
                + " performed " + action
                + " successfully. SKU: " + sku
                + ". Quantity changed: " + quantity;

        InventoryLog log = InventoryLog.builder().timestamp(LocalDateTime.now()).username(username).action(action).detail(detail).build();
        inventoryLogRepository.save(log);
    }
}