package com.example.doan.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.doan.entity.SanPham;
import com.example.doan.repository.SanPhamRepository;

@Service
public class sanphamService {

    @Autowired
    SanPhamRepository<SanPham> sanphamRepository;

    @Transactional
    public List<SanPham> getAllsanpham() {
        return (List<SanPham>) sanphamRepository.findAll();
    }

    @Transactional
    public Optional<SanPham> getById(Long id) {
        return sanphamRepository.findById(id);
    }

    @Transactional
    public void deletesanpham(Long sanphamId) {
        sanphamRepository.deleteById(sanphamId);
    }

    @Transactional
    public boolean addsanpham(SanPham sanpham) {
        return sanphamRepository.save(sanpham) != null;
    }


    public SanPham updatesanpham(Long id_SP, SanPham sanphamEntity) {
        SanPham updatedSP;
        Optional<SanPham> searchEntity = sanphamRepository.findById(id_SP);
        if (searchEntity.isPresent()) {
            SanPham publisher = searchEntity.get();
            publisher.setTenSP(sanphamEntity.getTenSP());
            publisher.setNhacungcap(sanphamEntity.getNhacungcap());
            publisher.setSoluong_SP(sanphamEntity.getSoluong_SP());
            publisher.setGiaban_SP(sanphamEntity.getGiaban_SP());
            publisher.setNgaynhap_ma(sanphamEntity.getNgaynhap_ma());
            publisher.setNgaysx_SP(sanphamEntity.getNgaysx_SP());
            publisher.setHansd_SP(sanphamEntity.getHansd_SP());
            publisher.setPhanloai(sanphamEntity.getPhanloai());
            publisher.setHinhanh_SP(sanphamEntity.getHinhanh_SP());
            updatedSP = sanphamRepository.save(publisher);
        } else {
            throw new EntityNotFoundException();
        }
        return updatedSP;
    }


}

