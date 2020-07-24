package com.example.doan.controller;

import java.util.List;
import java.util.Optional;

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

import com.example.doan.entity.KhachHang;
import com.example.doan.service.khachhangService;

@CrossOrigin
@RestController
public class khachhangController {

    @Autowired
    khachhangService khachhangService;

    @RequestMapping(value = "/khachhangs", method = RequestMethod.GET) 

    // lấy tất cả
    public ResponseEntity<List<KhachHang>> findAllkhachhang() {
        List<KhachHang> khachhang = khachhangService.getAllkhachhang();
        if (khachhang.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(khachhang, HttpStatus.OK);
    }

    // lấy theo id
    @RequestMapping(value = "/khachhang/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Optional<KhachHang> getAllkhachhang(@PathVariable Long id) {
        return khachhangService.getById(id);
    }


    ///xóa
    @RequestMapping(value = "/delkhachhang/{id}", method = RequestMethod.DELETE)
    public HttpStatus deletekhachhang(@PathVariable Long id) {
        khachhangService.deletekhachhang(id);
        ;
        return HttpStatus.NO_CONTENT;
    }

    @RequestMapping(value = "/addkhachhang", method = RequestMethod.POST)
    public HttpStatus insertkhachhang(@RequestBody KhachHang khachhang) {
        return khachhangService.addkhachhang(khachhang) ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
    }

    @RequestMapping(value = "/updatekhachhang/{id_KH}", method = RequestMethod.PUT)
    public KhachHang updatekhachhang(@PathVariable(value = "id_KH") Long id_KH, @Validated @RequestBody KhachHang khachhangs) {
        return khachhangService.updatekhachhang(id_KH, khachhangs);
    }
}