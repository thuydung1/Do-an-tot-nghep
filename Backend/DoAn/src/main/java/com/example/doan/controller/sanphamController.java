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
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.doan.entity.SanPham;
import com.example.doan.service.sanphamService;

@CrossOrigin
@RestController
public class sanphamController {

	@Autowired
	sanphamService sanphamService;
	
	@RequestMapping(value = "/sanphams", method = RequestMethod.GET)
	

	public ResponseEntity<List<SanPham>> findAllsanpham() {
        List<SanPham> sanpham = sanphamService.getAllsanpham();
        if (sanpham.isEmpty()) {
            return new ResponseEntity<List<SanPham>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<SanPham>>(sanpham, HttpStatus.OK);
    }

	 @RequestMapping(value = "/sanpham/{id}", method = RequestMethod.GET)
		public @ResponseBody Optional<SanPham> getAllsanpham(@PathVariable Long id) {
			return sanphamService.getById(id);
		}
	 
	
	
	@RequestMapping(value = "/delsanpham/{id}", method = RequestMethod.DELETE)
	public HttpStatus deletesanpham(@PathVariable Long id) {
		sanphamService.deletesanpham(id);;
		return HttpStatus.NO_CONTENT;
	}

	@RequestMapping(value = "/addsanpham", method = RequestMethod.POST)
	public HttpStatus insertsanpham(@RequestBody SanPham sanpham) {
		return sanphamService.addsanpham(sanpham) ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
	}

	@RequestMapping(value = "/updatsanpham/{id_SP}", method = RequestMethod.PUT)
    public SanPham updatesanpham(@PathVariable(value = "id_SP") Long id_SP, @Validated @RequestBody SanPham sanphams){
        return sanphamService.updatesanpham(id_SP, sanphams);
	}
	

}