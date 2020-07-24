package com.example.doan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.doan.entity.PhieuNhapKho;


public interface PhieuNhapKhoRepository<P> extends JpaRepository<PhieuNhapKho, Long> {
}
