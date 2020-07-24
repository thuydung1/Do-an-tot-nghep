package com.example.doan.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import com.example.doan.dto.PhieuXuatKhoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.doan.entity.PhieuXuatKho;
import com.example.doan.repository.PhieuXuatKhoRepository;

@Service
public class phieuxuatkhoService {

    @Autowired
    PhieuXuatKhoRepository<PhieuXuatKho> phieuxuatkhoRepository;

    @Transactional
    public List<PhieuXuatKho> getAllphieuxuatkho() {
        return phieuxuatkhoRepository.findAll();
    }

    @Transactional
    public Optional<PhieuXuatKho> getById(Long id) {
        return phieuxuatkhoRepository.findById(id);
    }

    @Transactional
    public void deletephieuxuatkho(Long phieuxuatkhoId) {
        phieuxuatkhoRepository.deleteById(phieuxuatkhoId);
    }

    @Transactional
    public boolean addphieuxuatkho(PhieuXuatKho phieuxuatkho) {
        return phieuxuatkhoRepository.save(phieuxuatkho) != null;
    }


    public PhieuXuatKho updatephieuxuatkho(Long id_PX, PhieuXuatKhoDto phieuxuatkhoEntity) {
        PhieuXuatKho updatedPX;
        Optional<PhieuXuatKho> searchEntity = phieuxuatkhoRepository.findById(id_PX);
        if (searchEntity.isPresent()) {
            PhieuXuatKho publisher = searchEntity.get();
//			publisher.setTenPX(phieuxuatkhoEntity.getTenPX());
//			publisher.setsanpham(phieuxuatkhoEntity.getsanphams());
//			publisher.setnhanvien(phieuxuatkhoEntity.getnhanvien());
//			publisher.setKhachhang(phieuxuatkhoEntity.getKhachhang());
//			publisher.setsoluong(phieuxuatkhoEntity.getsoluong());
//			publisher.setgiaban(phieuxuatkhoEntity.getgiaban());
//			publisher.setngayxuat(phieuxuatkhoEntity.getngayxuat());
//			publisher.setdiachi(phieuxuatkhoEntity.getdiachi());
            updatedPX = phieuxuatkhoRepository.save(publisher);
        } else {
            throw new EntityNotFoundException();
        }
        return updatedPX;
    }

}

