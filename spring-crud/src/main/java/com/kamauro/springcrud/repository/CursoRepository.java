package com.kamauro.springcrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kamauro.springcrud.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long>{
    
}
