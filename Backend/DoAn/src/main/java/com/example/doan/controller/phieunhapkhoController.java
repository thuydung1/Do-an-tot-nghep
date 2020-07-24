package com.example.doan.controller;

import com.example.doan.dto.HangTonKhoDto;
import com.example.doan.dto.ProductDto;
import com.example.doan.entity.PhieuNhapKho;
import com.example.doan.entity.SanPham;
import com.example.doan.service.phieunhapkhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
public class phieunhapkhoController {

    @Autowired
    phieunhapkhoService phieunhapkhoService;

    @RequestMapping(value = "/phieunhapkhos", method = RequestMethod.GET) 


    public ResponseEntity<List<PhieuNhapKho>> findAllphieunhapkho() {
        List<PhieuNhapKho> phieunhapkho = phieunhapkhoService.getAllphieunhapkho();
        if (phieunhapkho.isEmpty()) {
            return new ResponseEntity<List<PhieuNhapKho>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<PhieuNhapKho>>(phieunhapkho, HttpStatus.OK);
    }

    @RequestMapping(value = "/phieunhapkho/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Optional<PhieuNhapKho> getAllphieunhapkho(@PathVariable Long id) {
        return phieunhapkhoService.getById(id);
    }

    @RequestMapping(value = "/hangTonKho", method = RequestMethod.GET)
    public ResponseEntity<List<HangTonKhoDto>> getHangTonKho() {
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
        return ResponseEntity.ok().body(hangTonKhoDtoList);
    }

  
    @RequestMapping(value = "/delphieunhapkho/{id}", method = RequestMethod.DELETE)
    public HttpStatus deletephieunhapkho(@PathVariable Long id) {
        phieunhapkhoService.deletephieunhapkho(id);
        ;
        return HttpStatus.NO_CONTENT;
    }

    @RequestMapping(value = "/addphieunhapkho", method = RequestMethod.POST)
    public HttpStatus insertphieunhapkho(@RequestBody PhieuNhapKho phieunhapkho) {
        return phieunhapkhoService.addphieunhapkho(phieunhapkho) ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
    }

    @RequestMapping(value = "/updatphieunhapkho/{id_PN}", method = RequestMethod.PUT)
    public PhieuNhapKho updatephieunhapkho(@PathVariable(value = "id_PN") Long id_PN, @Validated @RequestBody PhieuNhapKho phieunhapkhos) {
        return phieunhapkhoService.updatephieunhapkho(id_PN, phieunhapkhos);
    }
}