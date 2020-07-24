package com.example.doan.dto;

import java.util.Date;
import java.util.List;

public class PhieuXuatKhoDto {
    private String id_PX;
    private String tenPX;
    private List<SanPhamXuatKhoDto> sanphamsDto;
    private Long id_NV;
    private Long id_KH;
    private String diachi;
    private Date ngayxuat;

    public PhieuXuatKhoDto() {
    }

    public String getId_PX() {
        return id_PX;
    }

    public void setId_PX(String id_PX) {
        this.id_PX = id_PX;
    }

    public String getTenPX() {
        return tenPX;
    }

    public void setTenPX(String tenPX) {
        this.tenPX = tenPX;
    }

    public List<SanPhamXuatKhoDto> getSanphamsDto() {
        return sanphamsDto;
    }

    public void setSanphamsDto(List<SanPhamXuatKhoDto> sanphamsDto) {
        this.sanphamsDto = sanphamsDto;
    }

    public Long getId_NV() {
        return id_NV;
    }

    public void setId_NV(Long id_NV) {
        this.id_NV = id_NV;
    }

    public Long getId_KH() {
        return id_KH;
    }

    public void setId_KH(Long id_KH) {
        this.id_KH = id_KH;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public Date getNgayxuat() {
        return ngayxuat;
    }

    public void setNgayxuat(Date ngayxuat) {
        this.ngayxuat = ngayxuat;
    }
}
