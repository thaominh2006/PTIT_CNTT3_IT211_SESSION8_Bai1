package com.example.ex1.repository;

import com.example.ex1.dto.InventoryReportDTO;
import com.example.ex1.entity.Product;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsBySku(String sku);

    @Modifying
    @Transactional
    @Query("""
           UPDATE Product p 
           SET p.quantity = p.quantity + :quantity 
           WHERE p.sku = :sku
           """)
    int stockIn(@Param("sku") String sku,
                @Param("quantity") Integer quantity);
    @Modifying
    @Transactional
    @Query("""
           UPDATE Product p 
           SET p.quantity = p.quantity - :quantity 
           WHERE p.sku = :sku AND p.quantity >= :quantity
           """)
    int stockOut(@Param("sku") String sku,
                 @Param("quantity") Integer quantity);

    @Query("""
       SELECT new com.example.ex1.dto.InventoryReportDTO(
           COALESCE(SUM(p.quantity), 0L),
           COALESCE(SUM(p.quantity * p.price), 0.0)
       )
       FROM Product p
       """)
    InventoryReportDTO inspectInventory();
}
