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
@Table(name = "PHANLOAI") 
public class PhanLoai {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MALSP") 
	Long id_LSP; 

	@NotNull
	@Column(name = "TENLSP")
	String tenLSP;
	
	

	public Long getId_LSP() {
		return id_LSP;
	}

	public void setId_LSP(Long id_LSP) {
		this.id_LSP = id_LSP;
	}

	public String getTenLSP() {
		return tenLSP;
	}

	public void setTenLSP(String tenLSP) {
		this.tenLSP = tenLSP;
	}

	
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "phanloai")
	private Collection<SanPham> sanphams; 
	

}

