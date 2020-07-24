package com.example.doan.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.doan.entity.PhanLoai;

public interface PhanLoaiRepository<P> extends CrudRepository<PhanLoai, Long> {

}
