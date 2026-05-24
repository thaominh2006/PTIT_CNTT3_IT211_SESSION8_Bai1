package com.example.ex1.service.impl;

import com.example.ex1.dto.InventoryReportDTO;
import com.example.ex1.repository.ProductRepository;
import com.example.ex1.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public void stockIn(String sku, Integer quantity, String username, String role) {
        if (!productRepository.existsBySku(sku)) {
            throw new RuntimeException("Không tìm thấy sản phẩm có SKU: " + sku);
        }
        productRepository.stockIn(sku, quantity);
    }

    @Override
    public void stockOut(String sku, Integer quantity, String username, String role) {
        if (!productRepository.existsBySku(sku)) {
            throw new RuntimeException("Không tìm thấy sản phẩm có SKU: " + sku);
        }
        int updatedRows = productRepository.stockOut(sku, quantity);
        if (updatedRows == 0) {
            throw new RuntimeException("Số lượng tồn kho không đủ");
        }
    }

    @Override
    public InventoryReportDTO inspectInventory(String username, String role) {
        return productRepository.inspectInventory();
    }

    @Override
    public void deleteProduct(Long id, String username, String role) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Không tìm thấy sản phẩm có id: " + id);
        }
        productRepository.deleteById(id);
    }
}
