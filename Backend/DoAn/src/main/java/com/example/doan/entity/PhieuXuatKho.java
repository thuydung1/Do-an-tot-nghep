package com.example.doan.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import com.sun.istack.NotNull;

import lombok.Data;

@Entity
@Data
@Table(name = "PHIEUXUATKHO") 
public class PhieuXuatKho {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MAPX") 
	Long id_PX;
	@NotNull
	@Column(name = "TENPX")
	String tenPX;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "phieuXuatKho")
	private List<PhieuXuatKhoSanPham> phieuXuatKhoSanPhams;

	@ManyToOne
	@JoinColumn(name = "MANV")
	private NhanVien nhanvien;

	@ManyToOne
	@JoinColumn(name = "MAKH")
	private KhachHang khachhang;

	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "MM-dd-yyyy")
	@Column(name = "NGAYXUAT")
	Date ngayxuat;

	@NotNull
	@Column(name = "DIACHI")
	String diachi;

	@Column(name = "TONG_TIEN")
	Integer tongTien;
	@Column(name = "CHIETKHAU")
	Integer chieuKhau;
	@Column(name = "TONG_THANH_TOAN")
	Integer tongThanhToan;

}
