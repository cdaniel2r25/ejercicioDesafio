package com.example.ejercicio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ejercicio.models.Phones;

public interface PhonesRepository extends JpaRepository<Phones, String> {

}
