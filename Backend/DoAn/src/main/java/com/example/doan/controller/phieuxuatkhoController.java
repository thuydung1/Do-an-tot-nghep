package com.example.doan.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.doan.dto.PhieuXuatKhoDto;
import com.example.doan.dto.SanPhamXuatKhoDto;
import com.example.doan.entity.*;
import com.example.doan.repository.KhachHangRepository;
import com.example.doan.repository.NhanVienRepository;
import com.example.doan.repository.SanPhamRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.doan.service.phieuxuatkhoService;

@CrossOrigin
@RestController
public class phieuxuatkhoController {

    @Autowired
    phieuxuatkhoService phieuxuatkhoService;
    @Autowired
    SanPhamRepository sanPhamRepository;
    @Autowired
    KhachHangRepository khRepository;
    @Autowired
    NhanVienRepository nhanVienRepository;

    @RequestMapping(value = "/phieuxuatkhos", method = RequestMethod.GET) 

 
    public ResponseEntity<List<PhieuXuatKho>> findAllphieuxuatkho() {
        List<PhieuXuatKho> phieuxuatkho = phieuxuatkhoService.getAllphieuxuatkho();
        if (phieuxuatkho.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(phieuxuatkho, HttpStatus.OK);
    }

 
    @RequestMapping(value = "/phieuxuatkho/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Optional<PhieuXuatKho> getAllphieuxuatkho(@PathVariable Long id) {
        return phieuxuatkhoService.getById(id);
    }


 
    @RequestMapping(value = "/delphieuxuatkho/{id}", method = RequestMethod.DELETE)
    public HttpStatus deletephieuxuatkho(@PathVariable Long id) {
        phieuxuatkhoService.deletephieuxuatkho(id);
        return HttpStatus.NO_CONTENT;
    }

    @RequestMapping(value = "/addphieuxuatkho", method = RequestMethod.POST)
    public HttpStatus insertphieuxuatkho(@RequestBody PhieuXuatKhoDto phieuXuatKhoDto) {
        PhieuXuatKho pxk = new PhieuXuatKho();
        pxk.setId_PX(Strings.isNotBlank(phieuXuatKhoDto.getId_PX()) ?
                (Long.parseLong(phieuXuatKhoDto.getId_PX())) : null);
        List<PhieuXuatKhoSanPham> phieuXuatKhoSanPhams = new ArrayList<>();
        for (SanPhamXuatKhoDto sanPhamXuatKhoDto : phieuXuatKhoDto.getSanphamsDto()) {
            PhieuXuatKhoSanPham phieuXuatKhoSanPham = new PhieuXuatKhoSanPham();
            phieuXuatKhoSanPham.setId(sanPhamXuatKhoDto.getId());
            phieuXuatKhoSanPham.setSanpham((SanPham) sanPhamRepository.findById(sanPhamXuatKhoDto.getId_SP()).get());
            phieuXuatKhoSanPham.setGiaban(Integer.valueOf(sanPhamXuatKhoDto.getGiaban()));
            phieuXuatKhoSanPham.setSoluong(Integer.valueOf(sanPhamXuatKhoDto.getSoluong()));
            phieuXuatKhoSanPham.setPhieuXuatKho(pxk);
            phieuXuatKhoSanPhams.add(phieuXuatKhoSanPham);
        }
        pxk.setPhieuXuatKhoSanPhams(phieuXuatKhoSanPhams);
        pxk.setTenPX(phieuXuatKhoDto.getTenPX());
        pxk.setDiachi(phieuXuatKhoDto.getDiachi());
        pxk.setKhachhang((KhachHang) khRepository.findById(phieuXuatKhoDto.getId_KH()).get());
        pxk.setNgayxuat(phieuXuatKhoDto.getNgayxuat());
        pxk.setNhanvien((NhanVien) nhanVienRepository.findById(phieuXuatKhoDto.getId_NV()).get());
        final int sum = phieuXuatKhoSanPhams.stream().mapToInt(x -> x.getSoluong() * x.getGiaban()).sum();
        pxk.setTongTien(sum);
        if (sum > 10000000) {
            pxk.setChieuKhau(2);
            pxk.setTongThanhToan(sum * 98 / 100);
        } else {
            pxk.setChieuKhau(0);
            pxk.setTongThanhToan(sum);
        }
        return phieuxuatkhoService.addphieuxuatkho(pxk) ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
    }
}