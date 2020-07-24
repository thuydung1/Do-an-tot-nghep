package com.example.doan.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
@Table(name = "PHIEUXUATKHOSANPHAM")
public class PhieuXuatKhoSanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "idsanpham")
    private SanPham sanpham;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idphieuxuatkho", nullable = false)
    private PhieuXuatKho phieuXuatKho;

    @Column(name = "soluong")
    private int soluong;

    @Column(name = "giaban")
    private int giaban;

}
