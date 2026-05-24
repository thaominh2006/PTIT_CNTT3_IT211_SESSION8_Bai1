package com.example.ex1.service;

import com.example.ex1.dto.InventoryReportDTO;

public interface ProductService {
    void stockIn(String sku, Integer quantity, String username, String role);
    void stockOut(String sku, Integer quantity, String username, String role);
    InventoryReportDTO inspectInventory(String username, String role);
    void deleteProduct(Long id, String username, String role);
}
