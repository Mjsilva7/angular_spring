package com.kamauro.springcrud.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kamauro.springcrud.model.Curso;
import com.kamauro.springcrud.repository.CursoRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/api/cursos")
@Validated
public class CursoController {

    private final CursoRepository cursoRepository;

    public CursoController(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    @GetMapping
    public List<Curso> lista() {
        return cursoRepository.findAll();
    }  
    
    @GetMapping("/{id}")
    public ResponseEntity<Curso> buscarPorId(@PathVariable("id") @NotNull @Positive Long id) {
        return cursoRepository.findById(id)
                    .map(record -> ResponseEntity.ok().body(record))
                    .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Curso create(@RequestBody @Valid Curso curso) {
        return cursoRepository.save(curso);
    }    
    //Outra maneira de fazer o post caso precise manusear dados com cabeçalho e outros
    // @PostMapping
    // public ResponseEntity<Curso> create(@RequestBody Curso curso) {
    //     return ResponseEntity.status(HttpStatus.CREATED).body(cursoRepository.save(curso));
    // }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> update(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid Curso curso) {
        return cursoRepository.findById(id)
                    .map(record -> {
                        record.setName(curso.getName());
                        record.setCategory(curso.getCategory());
                        Curso updated = cursoRepository.save(record);
                        return ResponseEntity.ok().body(updated);
                    })
                    .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @NotNull @Positive Long id) {
        return cursoRepository.findById(id)
                    .map(record -> {
                        cursoRepository.deleteById(id);
                        return ResponseEntity.noContent().<Void>build();
                    })
                    .orElse(ResponseEntity.notFound().build());

    }
   
}
