package com.example.doan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.doan.entity.PhieuXuatKho;


public interface PhieuXuatKhoRepository<P> extends JpaRepository<PhieuXuatKho, Long> {

}
