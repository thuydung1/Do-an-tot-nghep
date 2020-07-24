package com.example.doan.controller;

import com.example.doan.dto.HangTonKhoDto;
import com.example.doan.dto.ProductDto;
import com.example.doan.dto.TongQuanDto;
import com.example.doan.entity.PhieuNhapKho;
import com.example.doan.entity.SanPham;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
public class TongQuanController {

    @Autowired
    com.example.doan.service.phieunhapkhoService phieunhapkhoService;

    @Autowired
    com.example.doan.service.phieuxuatkhoService phieuxuatkhoService;

    @Autowired
    com.example.doan.service.sanphamService sanphamService;

    @RequestMapping(value = "/tongquan", method = RequestMethod.GET)
    public ResponseEntity<TongQuanDto> getTongQuan() {
        TongQuanDto tongQuanDto = new TongQuanDto();
        tongQuanDto.setTongSoNhapKho(phieunhapkhoService.getAllphieunhapkho().size());
        tongQuanDto.setTongSoXuatKho(phieuxuatkhoService.getAllphieuxuatkho().size());
        tongQuanDto.setTongSoTienXuatKho(phieuxuatkhoService.getAllphieuxuatkho().stream().mapToLong(x -> x.getTongTien()).sum());
        tongQuanDto.setTongSoTienNhapKho(phieunhapkhoService.getAllphieunhapkho().stream().mapToLong(x -> Integer.valueOf(x.getGianhap()) * Integer.valueOf(x.getSoluong())).sum());
        tongQuanDto.setSanPhamList(sanphamService.getAllsanpham().stream().limit(4).collect(Collectors.toList()));
        tongQuanDto.setLai(tongQuanDto.getTongSoTienXuatKho() - tongQuanDto.getTongSoTienNhapKho());
        tongQuanDto.setHangtonkho(this.getHangTonKho());
        return ResponseEntity.ok().body(tongQuanDto);
    }

    private List<HangTonKhoDto> getHangTonKho() {
        List<PhieuNhapKho> allPhieuNhapKho = phieunhapkhoService.getAllphieunhapkho();
        List<HangTonKhoDto> hangTonKhoDtoList = new ArrayList<>();
        Map<SanPham, List<PhieuNhapKho>> studlistGrouped =
                allPhieuNhapKho.stream().collect(Collectors.groupingBy(w -> w.getsanpham()));
        for (Map.Entry<SanPham, List<PhieuNhapKho>> entry : studlistGrouped.entrySet()) {
            HangTonKhoDto hangTonKhoDto = new HangTonKhoDto();
            hangTonKhoDto.setProduct(entry.getKey());
            List<ProductDto> productDtos = entry.getValue().stream().map(PhieuNhapKho -> new ProductDto(PhieuNhapKho.getSoluong(), PhieuNhapKho.getMangaynhap())).sorted().collect(Collectors.toList());
            hangTonKhoDto.setProductDtoList(productDtos);
            int collect = productDtos.stream().map(x -> Integer.valueOf(x.getAmount())).collect(Collectors.summingInt(Integer::intValue));
            hangTonKhoDto.setTotalAmount(collect);
            hangTonKhoDtoList.add(hangTonKhoDto);
        }
        return hangTonKhoDtoList.stream().limit(3).collect(Collectors.toList());
    }
}
