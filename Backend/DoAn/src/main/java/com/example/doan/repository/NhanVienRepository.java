package com.example.doan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.example.doan.entity.NhanVien;

public interface NhanVienRepository<P> extends JpaRepository<NhanVien, Long> {

}
