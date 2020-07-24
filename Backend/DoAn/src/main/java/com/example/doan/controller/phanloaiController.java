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

import com.example.doan.entity.PhanLoai;
import com.example.doan.service.phanloaiService;

@CrossOrigin
@RestController
public class phanloaiController {

	@Autowired
	phanloaiService phanloaiService;
	
	@RequestMapping(value = "/phanloais", method = RequestMethod.GET)
	
	
	public ResponseEntity<List<PhanLoai>> findAllphanloai() {
        List<PhanLoai> phanloai = phanloaiService.getAllphanloai();
        if (phanloai.isEmpty()) {
            return new ResponseEntity<List<PhanLoai>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<PhanLoai>>(phanloai, HttpStatus.OK);
    }
	
	
	 @RequestMapping(value = "/phanloai/{id}", method = RequestMethod.GET)
		public @ResponseBody Optional<PhanLoai> getAllphanloai(@PathVariable Long id) {
			return phanloaiService.getById(id);
		}
	 
	
		
	@RequestMapping(value = "/delphanloai/{id}", method = RequestMethod.DELETE)
	public HttpStatus deletephanloai(@PathVariable Long id) {
		phanloaiService.deletephanloai(id);;
		return HttpStatus.NO_CONTENT;
	}

	@RequestMapping(value = "/addphanloai", method = RequestMethod.POST)
	public HttpStatus insertphanloai(@RequestBody PhanLoai phanloai) {
		return phanloaiService.addphanloai(phanloai) ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
	}

	@RequestMapping(value = "/updatphanloai/{id_PL}", method = RequestMethod.PUT)
    public PhanLoai updatephanloai(@PathVariable(value = "id_PL") Long id_PL, @Validated @RequestBody PhanLoai phanloais){
        return phanloaiService.updatephanloai(id_PL, phanloais);
	}
}