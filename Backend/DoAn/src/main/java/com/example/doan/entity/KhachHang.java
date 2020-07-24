package com.example.doan.entity;


import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name = "KHACHHANG") 
public class KhachHang {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MAKH") 
	Long id_KH; 

	@NotNull
	@Column(name = "TENKH")
	String tenKH;
	
	@NotNull
	@Column(name = "SDT") 
	String sdtKH;
	
	@NotNull
	@Column(name = "DIACHI")
	String diachiKH;
	
	@NotNull
	@Column(name = "STK")
	String stkKH;
	
	

	public Long getId_KH() {
		return id_KH;
	}

	public void setId_KH(Long id_KH) {
		this.id_KH = id_KH;
	}

	public String getTenKH() {
		return tenKH;
	}

	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
	}

	public String getSdtKH() {
		return sdtKH;
	}

	public void setSdtKH(String sdtKH) {
		this.sdtKH = sdtKH;
	}

	public String getDiachiKH() {
		return diachiKH;
	}

	public void setDiachiKH(String diachiKH) {
		this.diachiKH = diachiKH;
	}

	public String getStkKH() {
		return stkKH;
	}

	public void setStkKH(String stkKH) {
		this.stkKH = stkKH;
	}
	
	//
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "khachhang")
	private Collection<PhieuXuatKho> phieuxuatkhos;
}

