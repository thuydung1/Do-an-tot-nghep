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

import com.example.doan.entity.NhaCungCap;
import com.example.doan.service.nhacungcapService;

@CrossOrigin
@RestController
public class nhacungcapController {

	@Autowired
	nhacungcapService nhacungcapService;
	
	@RequestMapping(value = "/nhacungcaps", method = RequestMethod.GET)
	
	
	public ResponseEntity<List<NhaCungCap>> findAllNhacungcap() {
        List<NhaCungCap> nhacungcap = nhacungcapService.getAllnhacungcap();
        if (nhacungcap.isEmpty()) {
            return new ResponseEntity<List<NhaCungCap>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<NhaCungCap>>(nhacungcap, HttpStatus.OK);
    }
	
	
	 @RequestMapping(value = "/nhacungcap/{id}", method = RequestMethod.GET)
		public @ResponseBody Optional<NhaCungCap> getAllnhacungcap(@PathVariable Long id) {
			return nhacungcapService.getById(id);
		}
	 
	
	
	@RequestMapping(value = "/delnhacungcap/{id}", method = RequestMethod.DELETE)
	public HttpStatus deleteNhacungcap(@PathVariable Long id) {
		nhacungcapService.deletenhacungcap(id);;
		return HttpStatus.NO_CONTENT;
	}

	@RequestMapping(value = "/addnhacungcap", method = RequestMethod.POST)
	public HttpStatus insertnhacungcap(@RequestBody NhaCungCap nhacungcap) {
		return nhacungcapService.addnhacungcap(nhacungcap) ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
	}

	@RequestMapping(value = "/updatenhacungcap/{id_NCC}", method = RequestMethod.PUT)
    public NhaCungCap updateNhacungcap(@PathVariable(value = "id_NCC") Long id_NCC, @Validated @RequestBody NhaCungCap nhacungcaps){
        return nhacungcapService.updatenhacungcap(id_NCC, nhacungcaps);
	}
}