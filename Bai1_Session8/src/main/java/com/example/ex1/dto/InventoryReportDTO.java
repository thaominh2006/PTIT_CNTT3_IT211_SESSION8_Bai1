package com.example.ex1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InventoryReportDTO {
    private Long totalQuantity;
    private Double totalValue;
}