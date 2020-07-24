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
@Table(name = "NHACUNGCAP") 
public class NhaCungCap {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MANCC") 
	Long id_NCC; 

	@NotNull
	@Column(name = "TENNCC")
	String tenNCC;
	
	@NotNull
	@Column(name = "SDT")
	String sdtNCC;
	
	@NotNull
	@Column(name = "DIACHI")
	String diachiNCC;
	
	@NotNull
	@Column(name = "STK")
	String stkNCC;
	
	

	public Long getId_NCC() {
		return id_NCC;
	}

	public void setId_NCC(Long id_NCC) {
		this.id_NCC = id_NCC;
	}

	public String getTenNCC() {
		return tenNCC;
	}

	public void setTenNCC(String tenNCC) {
		this.tenNCC = tenNCC;
	}

	public String getSdtNCC() {
		return sdtNCC;
	}

	public void setSdtNCC(String sdtNCC) {
		this.sdtNCC = sdtNCC;
	}

	public String getDiachiNCC() {
		return diachiNCC;
	}

	public void setDiachiNCC(String diachiNCC) {
		this.diachiNCC = diachiNCC;
	}

	public String getStkNCC() {
		return stkNCC;
	}

	public void setStkNCC(String stkNCC) {
		this.stkNCC = stkNCC;
	}
	
	//  
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "nhacungcap")
	private Collection<SanPham> sanphams;
	

}

