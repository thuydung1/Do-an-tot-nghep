package com.example.doan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.doan.entity.KhachHang;

import java.util.Optional;

public interface KhachHangRepository<P> extends JpaRepository<KhachHang, Long> {
    @Override
    Optional<KhachHang> findById(Long aLong);
}
