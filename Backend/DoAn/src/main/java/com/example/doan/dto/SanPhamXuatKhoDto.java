package com.example.doan.dto;

public class SanPhamXuatKhoDto {
    private Long id;
    private Long id_SP;
    private String soluong;
    private String giaban;

    public SanPhamXuatKhoDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_SP() {
        return id_SP;
    }

    public void setId_SP(Long id_SP) {
        this.id_SP = id_SP;
    }

    public String getSoluong() {
        return soluong;
    }

    public void setSoluong(String soluong) {
        this.soluong = soluong;
    }

    public String getGiaban() {
        return giaban;
    }

    public void setGiaban(String giaban) {
        this.giaban = giaban;
    }
}
