package com.example.doan.entity;


import java.util.Date;

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

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import com.sun.istack.NotNull;

@Entity
@Data
@Table(name = "PHIEUNHAPKHO") 
public class PhieuNhapKho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MAPN") 
            Long id_PN; 
    @NotNull
    @Column(name = "TENPN")
    String tenPN;

    @ManyToOne
    @JoinColumn(name = "MASP")
    private SanPham sanpham;

    public SanPham getsanpham() {
        return sanpham;
    }

    public void setsanpham(SanPham sanpham) {
        this.sanpham = sanpham;
    }

    @ManyToOne
    @JoinColumn(name = "MANV")
    private NhanVien nhanvien;

    public NhanVien getnhanvien() {
        return nhanvien;
    }

    public void setnhanvien(NhanVien nhanvien) {
        this.nhanvien = nhanvien;
    }

    @ManyToOne
    @JoinColumn(name = "MANCC")
    private NhaCungCap nhacungcap;

    @NotNull
    @Column(name = "SOLUONG")
    String soluong;

    @NotNull
    @Column(name = "GIANHAP")
    String gianhap;

    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "MM-dd-yyyy")
    @Column(name = "MANN")
    Date mangaynhap;

    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "MM-dd-yyyy")
    @Column(name = "NGAYSX")
    Date ngaysx;

    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "MM-dd-yyyy")
    @Column(name = "HANSD")
    Date hansd;

    @ManyToOne
    @JoinColumn(name = "MALSP")
    private PhanLoai phanloai;

}

