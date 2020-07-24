package com.example.doan.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.doan.entity.PhieuNhapKho;
import com.example.doan.repository.PhieuNhapKhoRepository;

@Service
public class phieunhapkhoService {

    @Autowired
    PhieuNhapKhoRepository<PhieuNhapKho> phieunhapkhoRepository;

    @Transactional
    public List<PhieuNhapKho> getAllphieunhapkho() {
        return phieunhapkhoRepository.findAll();
    }

    @Transactional
    public Optional<PhieuNhapKho> getById(Long id) {
        return phieunhapkhoRepository.findById(id);
    }

    @Transactional
    public void deletephieunhapkho(Long phieunhapkhoId) {
        phieunhapkhoRepository.deleteById(phieunhapkhoId);
    }

    @Transactional
    public boolean addphieunhapkho(PhieuNhapKho phieunhapkho) {
        return phieunhapkhoRepository.save(phieunhapkho) != null;
    }

    public PhieuNhapKho updatephieunhapkho(Long id_PN, PhieuNhapKho phieuNhapKhoEntity) {
        PhieuNhapKho updatedPN;
        Optional<PhieuNhapKho> searchEntity = phieunhapkhoRepository.findById(id_PN);
        if (searchEntity.isPresent()) {
            PhieuNhapKho publisher = searchEntity.get();
            publisher.setTenPN(phieuNhapKhoEntity.getTenPN());
            publisher.setsanpham(phieuNhapKhoEntity.getsanpham());
            publisher.setnhanvien(phieuNhapKhoEntity.getnhanvien());
            publisher.setNhacungcap(phieuNhapKhoEntity.getNhacungcap());
            publisher.setSoluong(phieuNhapKhoEntity.getSoluong());
            publisher.setGianhap(phieuNhapKhoEntity.getGianhap());
            publisher.setMangaynhap(phieuNhapKhoEntity.getMangaynhap());
            publisher.setPhanloai(phieuNhapKhoEntity.getPhanloai());
            publisher.setHansd(phieuNhapKhoEntity.getHansd());
            publisher.setNgaysx(phieuNhapKhoEntity.getNgaysx());
            updatedPN = phieunhapkhoRepository.save(publisher);
        } else {
            throw new EntityNotFoundException();
        }
        return updatedPN;
    }

}

