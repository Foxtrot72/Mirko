package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PaintingRepository extends JpaRepository<Quadro, Long> {

}