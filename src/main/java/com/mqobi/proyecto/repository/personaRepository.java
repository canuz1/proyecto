package com.mqobi.proyecto.repository;

import com.mqobi.proyecto.model.persona;


import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface personaRepository extends JpaRepository<persona, Long> {

}