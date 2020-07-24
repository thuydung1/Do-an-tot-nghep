package com.example.doan.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.doan.entity.NhaCungCap;
import com.example.doan.repository.NhaCungCapRepository;

@Service
public class nhacungcapService {

	@Autowired
    NhaCungCapRepository<NhaCungCap> nhacungcapRepository;

	@Transactional
	public List<NhaCungCap> getAllnhacungcap() {
		return (List<NhaCungCap>)  nhacungcapRepository.findAll();
	}

	@Transactional
	public Optional<NhaCungCap> getById(Long id) {
		return  nhacungcapRepository.findById(id);
	}

	@Transactional
	public void deletenhacungcap(Long nhacungcapId) {
		 nhacungcapRepository.deleteById(nhacungcapId);
	}

	@Transactional
	public boolean addnhacungcap(NhaCungCap nhacungcap) {
		return  nhacungcapRepository.save(nhacungcap) != null;
	}

	public NhaCungCap updatenhacungcap(Long id_NCC, NhaCungCap nhaCungCapEntity) {
		NhaCungCap updatedNCC;
		Optional<NhaCungCap> searchEntity =  nhacungcapRepository.findById(id_NCC);
		if (searchEntity.isPresent()) {
			NhaCungCap publisher = searchEntity.get();
			publisher.setTenNCC(nhaCungCapEntity.getTenNCC());
			publisher.setDiachiNCC(nhaCungCapEntity.getDiachiNCC());
			publisher.setSdtNCC(nhaCungCapEntity.getSdtNCC());
			publisher.setStkNCC(nhaCungCapEntity.getStkNCC());
			updatedNCC = nhacungcapRepository.save(publisher);
		} else {
			throw new EntityNotFoundException();
		}
		return updatedNCC;
	}

}

