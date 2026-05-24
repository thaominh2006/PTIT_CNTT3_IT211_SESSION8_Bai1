package com.example.ex1.controller;

import com.example.ex1.dto.InventoryReportDTO;
import com.example.ex1.dto.StockRequestDTO;
import com.example.ex1.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/stock-in")
    public ResponseEntity<?> stockIn(
            @RequestHeader("X-User") String username,
            @RequestHeader("X-Role") String role,
            @Valid @RequestBody StockRequestDTO request
    ) {
        productService.stockIn(request.getSku(), request.getQuantity(), username, role);
        return ResponseEntity.ok(Map.of("message", "Nhập kho thành công!"));
    }

    @PostMapping("/stock-out")
    public ResponseEntity<?> stockOut(
            @RequestHeader("X-User") String username,
            @RequestHeader("X-Role") String role,
            @Valid @RequestBody StockRequestDTO request
    ) {
        productService.stockOut(request.getSku(), request.getQuantity(), username, role);
        return ResponseEntity.ok(Map.of("message", "Xuất kho thành công"!));
    }
    @GetMapping("/inspect")
    public ResponseEntity<InventoryReportDTO> inspectInventory(
            @RequestHeader("X-User") String username,
            @RequestHeader("X-Role") String role
    ) {
        return ResponseEntity.ok(productService.inspectInventory(username, role));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(
            @PathVariable Long id,
            @RequestHeader("X-User") String username,
            @RequestHeader("X-Role") String role
    ) {
        productService.deleteProduct(id, username, role);
        return ResponseEntity.ok(Map.of("message", "Xóa sản phẩm thành công!"));
    }
}