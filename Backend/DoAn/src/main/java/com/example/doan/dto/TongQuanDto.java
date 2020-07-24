package com.example.doan.dto;

import com.example.doan.entity.SanPham;
import lombok.Data;

import java.util.List;


@Data
public class TongQuanDto {
    private Integer tongSoNhapKho;
    private Long tongSoTienNhapKho;
    private Integer tongSoXuatKho;
    private Long tongSoTienXuatKho;
    private Long lai;
    private List<SanPham> sanPhamList;
    private List<HangTonKhoDto> hangtonkho;
}
