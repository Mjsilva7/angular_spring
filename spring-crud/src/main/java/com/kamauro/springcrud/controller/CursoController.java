package com.kamauro.springcrud.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kamauro.springcrud.model.Curso;
import com.kamauro.springcrud.repository.CursoRepository;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    private final CursoRepository cursoRepository;

    public CursoController(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    @GetMapping
    public List<Curso> lista() {
        return cursoRepository.findAll();
    }    

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Curso create(@RequestBody Curso curso) {
        return cursoRepository.save(curso);
    }    
    //Outra maneira de fazer o post caso precise manusear dados com cabeçalho e outros
    // @PostMapping
    // public ResponseEntity<Curso> create(@RequestBody Curso curso) {
    //     return ResponseEntity.status(HttpStatus.CREATED).body(cursoRepository.save(curso));
    // }
   
}
