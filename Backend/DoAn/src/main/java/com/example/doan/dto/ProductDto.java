package com.example.doan.dto;

import java.util.Comparator;
import java.util.Date;

public final class ProductDto implements Comparable<ProductDto> {
    private String amount;
    private Date ngayNhap;

    public ProductDto(String amount, Date ngayNhap) {
        this.amount = amount;
        this.ngayNhap = ngayNhap;
    }

    public ProductDto() {
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    @Override
    public int compareTo(ProductDto o) {
        return o.getNgayNhap().compareTo(this.getNgayNhap());
    }
}
