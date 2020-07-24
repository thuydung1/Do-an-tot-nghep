package com.example.doan.dto;

import com.example.doan.entity.SanPham;
import lombok.Data;

import java.util.List;

@Data
public class HangTonKhoDto {
    private SanPham product;
    private List<ProductDto> productDtoList;
    private int totalAmount;

    public HangTonKhoDto() {
    }

}
