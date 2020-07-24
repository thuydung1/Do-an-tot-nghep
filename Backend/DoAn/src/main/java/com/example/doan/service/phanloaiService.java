package com.example.doan.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.doan.entity.PhanLoai;
import com.example.doan.repository.PhanLoaiRepository;

@Service
public class phanloaiService {

	@Autowired
	PhanLoaiRepository<PhanLoai> phanloaiRepository;

	@Transactional
	public List<PhanLoai> getAllphanloai() {
		return (List<PhanLoai>)  phanloaiRepository.findAll();
	}

	@Transactional
	public Optional<PhanLoai> getById(Long id) {
		return  phanloaiRepository.findById(id);
	}

	@Transactional
	public void deletephanloai(Long phanloaiId) {
		phanloaiRepository.deleteById(phanloaiId);
	}

	@Transactional
	public boolean addphanloai(PhanLoai phanloai) {
		return  phanloaiRepository.save(phanloai) != null;
	}

	public PhanLoai updatephanloai(Long id_PL, PhanLoai phanloaiEntity) {
		PhanLoai updatedPL;
		Optional<PhanLoai> searchEntity =  phanloaiRepository.findById(id_PL);
		if (searchEntity.isPresent()) {
			PhanLoai publisher = searchEntity.get();
			publisher.setTenLSP(phanloaiEntity.getTenLSP());
			updatedPL = phanloaiRepository.save(publisher);
		} else {
			throw new EntityNotFoundException();
		}
		return updatedPL;
	}

}

