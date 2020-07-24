package com.example.doan.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.doan.entity.KhachHang;
import com.example.doan.repository.KhachHangRepository;

@Service
public class khachhangService {

    @Autowired
    KhachHangRepository<KhachHang> khachhangRepository;

    @Transactional
    public List<KhachHang> getAllkhachhang() {
        return khachhangRepository.findAll();
    }

    @Transactional
    public Optional<KhachHang> getById(Long id) {
        return khachhangRepository.findById(id);
    }

    @Transactional
    public void deletekhachhang(Long khachhangId) {
        khachhangRepository.deleteById(khachhangId);
    }

    @Transactional
    public boolean addkhachhang(KhachHang khachhang) {
        return khachhangRepository.save(khachhang) != null;
    }

    public KhachHang updatekhachhang(Long id_KH, KhachHang khachHangEntity) {
        KhachHang updatedKH;
        Optional<KhachHang> searchEntity = khachhangRepository.findById(id_KH);
        if (searchEntity.isPresent()) {
            KhachHang publisher = searchEntity.get();
            publisher.setTenKH(khachHangEntity.getTenKH());
            publisher.setDiachiKH(khachHangEntity.getDiachiKH());
            publisher.setSdtKH(khachHangEntity.getSdtKH());
            publisher.setStkKH(khachHangEntity.getStkKH());
            updatedKH = khachhangRepository.save(publisher);
        } else {
            throw new EntityNotFoundException();
        }
        return updatedKH;
    }

}

