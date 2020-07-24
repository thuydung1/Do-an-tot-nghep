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

import com.example.doan.entity.NhanVien;
import com.example.doan.service.nhanvienService;

@CrossOrigin
@RestController
public class nhanvienController {

	@Autowired
	nhanvienService nhanvienService;
	
	@RequestMapping(value = "/nhanviens", method = RequestMethod.GET) 
	
	
	public ResponseEntity<List<NhanVien>> findAllnhanvien() {
        List<NhanVien> nhanvien = nhanvienService.getAllnhanvien();
        if (nhanvien.isEmpty()) {
            return new ResponseEntity<List<NhanVien>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<NhanVien>>(nhanvien, HttpStatus.OK);
    }
	
	
	 @RequestMapping(value = "/nhanvien/{id}", method = RequestMethod.GET)
		public @ResponseBody Optional<NhanVien> getAllnhanvien(@PathVariable Long id) {
			return nhanvienService.getById(id);
		}
	 
	
		
	@RequestMapping(value = "/delnhanvien/{id}", method = RequestMethod.DELETE)
	public HttpStatus deletenhanvien(@PathVariable Long id) {
		nhanvienService.deletenhanvien(id);;
		return HttpStatus.NO_CONTENT;
	}

	@RequestMapping(value = "/addnhanvien", method = RequestMethod.POST)
	public HttpStatus insertnhanvien(@RequestBody NhanVien nhanvien) {
		return nhanvienService.addnhanvien(nhanvien) ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
	}

	@RequestMapping(value = "/updatnhanvien/{id_NV}", method = RequestMethod.PUT)
    public NhanVien updatenhanvien(@PathVariable(value = "id_NV") Long id_NV, @Validated @RequestBody NhanVien nhanviens){
        return nhanvienService.updatenhanvien(id_NV, nhanviens);
	}
}