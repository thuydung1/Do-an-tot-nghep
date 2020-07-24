package com.example.doan.entity;


import java.util.Date;
import java.util.Objects;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import com.sun.istack.NotNull;

@Entity
@Table(name = "SANPHAM")
public class SanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MASP")
    private Long id_SP;

    @NotNull
    @Column(name = "TENSP")
    private String tenSP;

    /// lien ket
    @ManyToOne
    @JoinColumn(name = "MANCC")
    private NhaCungCap nhacungcap;

    public NhaCungCap getNhacungcap() {
        return nhacungcap;
    }

    public void setNhacungcap(NhaCungCap nhacungcap) {
        this.nhacungcap = nhacungcap;
    }

    @NotNull
    @Column(name = "SOLUONG")
    private String soluong_SP;

    @NotNull
    @Column(name = "GIABAN")
    private String giaban_SP;

    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "MM-dd-yyyy")
    @Column(name = "MANN")
    private Date ngaynhap_ma;

    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "MM-dd-yyyy")
    @Column(name = "NGAYSX")
    private Date ngaysx_SP;

    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "MM-dd-yyyy")
    @Column(name = "HANSD")
    private Date hansd_SP;

    @NotNull
    @Column(name = "hinhanh")
    private String hinhanh_SP;


    @ManyToOne
    @JoinColumn(name = "MALSP")
    private PhanLoai phanloai;

    public Long getId_SP() {
        return id_SP;
    }

    public void setId_SP(Long id_SP) {
        this.id_SP = id_SP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getSoluong_SP() {
        return soluong_SP;
    }

    public void setSoluong_SP(String soluong_SP) {
        this.soluong_SP = soluong_SP;
    }

    public String getGiaban_SP() {
        return giaban_SP;
    }

    public void setGiaban_SP(String giaban_SP) {
        this.giaban_SP = giaban_SP;
    }

    public Date getNgaynhap_ma() {
        return ngaynhap_ma;
    }

    public void setNgaynhap_ma(Date ngaynhap_ma) {
        this.ngaynhap_ma = ngaynhap_ma;
    }

    public Date getNgaysx_SP() {
        return ngaysx_SP;
    }

    public void setNgaysx_SP(Date ngaysx_SP) {
        this.ngaysx_SP = ngaysx_SP;
    }

    public Date getHansd_SP() {
        return hansd_SP;
    }

    public void setHansd_SP(Date hansd_SP) {
        this.hansd_SP = hansd_SP;
    }

    public String getHinhanh_SP() {
        return hinhanh_SP;
    }

    public void setHinhanh_SP(String hinhanh_SP) {
        this.hinhanh_SP = hinhanh_SP;
    }

    public PhanLoai getPhanloai() {
        return phanloai;
    }

    public void setPhanloai(PhanLoai phanloai) {
        this.phanloai = phanloai;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SanPham sanpham = (SanPham) o;
        return Objects.equals(id_SP, sanpham.id_SP);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_SP);
    }


//


}

