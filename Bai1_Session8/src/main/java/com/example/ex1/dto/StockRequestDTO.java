package com.example.ex1.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockRequestDTO {
    @NotBlank(message = "SKU không được để trống!")
    private String sku;

    @Min(value = 1, message = "Số lượng phải lớn hơn 0!")
    private Integer quantity;
}