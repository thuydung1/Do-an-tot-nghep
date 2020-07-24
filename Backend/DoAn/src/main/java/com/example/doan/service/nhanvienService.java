package com.example.doan.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.doan.entity.NhanVien;
import com.example.doan.repository.NhanVienRepository;

@Service
public class nhanvienService {

	@Autowired
    NhanVienRepository<NhanVien> nhanvienRepository;

	@Transactional
	public List<NhanVien> getAllnhanvien() {
		return (List<NhanVien>)  nhanvienRepository.findAll();
	}

	@Transactional
	public Optional<NhanVien> getById(Long id) {
		return  nhanvienRepository.findById(id);
	}

	@Transactional
	public void deletenhanvien(Long nhanvienId) {
		nhanvienRepository.deleteById(nhanvienId);
	}

	@Transactional
	public boolean addnhanvien(NhanVien nhanvien) {
		return  nhanvienRepository.save(nhanvien) != null;
	}

	public NhanVien updatenhanvien(Long id_NV, NhanVien nhanVienEntity) {
		NhanVien updatedNV;
		Optional<NhanVien> searchEntity =  nhanvienRepository.findById(id_NV);
		if (searchEntity.isPresent()) {
			NhanVien publisher = searchEntity.get();
			publisher.setTenNV(nhanVienEntity.getTenNV());
			publisher.setgioitinhNV(nhanVienEntity.getgioitinhNV());
			publisher.setchucvuNV(nhanVienEntity.getchucvuNV());
			publisher.setngaysinhNV(nhanVienEntity.getngaysinhNV());
			publisher.setcmndNV(nhanVienEntity.getcmndNV());
			publisher.setquequanNV(nhanVienEntity.getquequanNV());
			publisher.setstkNV(nhanVienEntity.getstkNV());
			publisher.setsdtNV(nhanVienEntity.getsdtNV());
			publisher.setdiachiNV(nhanVienEntity.getdiachiNV());
			publisher.setHinhAnh(nhanVienEntity.getHinhAnh());
			updatedNV = nhanvienRepository.save(publisher);
		} else {
			throw new EntityNotFoundException();
		}
		return updatedNV;
	}

}

