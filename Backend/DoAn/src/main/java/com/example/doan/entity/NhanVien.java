package com.example.doan.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.sun.istack.NotNull;

@Entity
@Table(name = "NHANVIEN") 
public class NhanVien {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MANV") 
	Long id_NV;

	@NotNull
	@Column(name = "TENNV")
	String tenNV;
	
	@NotNull
	@Column(name = "GIOITINH")
	String gioitinhNV;
	
	@NotNull
	@Column(name = "CHUCVU")
	String chucvuNV;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "MM-dd-yyyy")
	@Column(name = "NGAYSINH")
	Date ngaysinhNV;
	
	@NotNull
	@Column(name = "CMND")
	String cmndNV;
	
	@NotNull
	@Column(name = "QUEQUAN")
	String quequanNV;
	
	@NotNull
	@Column(name = "STK")
	String stkNV;
	
	@NotNull
	@Column(name = "SDT")
	String sdtNV;
	
	@NotNull
	@Column(name = "DIACHI")
	String diachiNV;
	
	@NotNull
	@Column(name = "HINHANH")
	String hinhAnh;
	
	

	public Long getId_NV() {
		return id_NV;
	}

	public void setId_NV(Long id_NV) {
		this.id_NV = id_NV;
	}

	public String getTenNV() {
		return tenNV;
	}

	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}

	public String getgioitinhNV() {
		return gioitinhNV;
	}

	public void setgioitinhNV(String gioitinhNV) {
		this.gioitinhNV = gioitinhNV;
	}

	public String getchucvuNV() {
		return chucvuNV;
	}

	public void setchucvuNV(String chucvuNV) {
		this.chucvuNV = chucvuNV;
	}

	public Date getngaysinhNV() {
		return ngaysinhNV;
	}

	public void setngaysinhNV(Date ngaysinhNV) {
		this.ngaysinhNV = ngaysinhNV;
	}
	
	public String getcmndNV() {
		return cmndNV;
	}

	public void setcmndNV(String cmndNV) {
		this.cmndNV = cmndNV;
	}
	public String getquequanNV() {
		return quequanNV;
	}

	public void setquequanNV(String quequanNV) {
		this.quequanNV = quequanNV;
	}
	public String getstkNV() {
		return stkNV;
	}

	public void setstkNV(String stkNV) {
		this.stkNV = stkNV;
	}
	public String getsdtNV() {
		return sdtNV;
	}

	public void setsdtNV(String sdtNV) {
		this.sdtNV = sdtNV;
	}
	public String getdiachiNV() {
		return diachiNV;
	}

	public void setdiachiNV(String diachiNV) {
		this.diachiNV = diachiNV;
	}
	
	public String getHinhAnh() {
		return hinhAnh;
	}

	public void setHinhAnh(String HinhAnh) {
		this.hinhAnh = HinhAnh;
	}
	
	//
	

}

