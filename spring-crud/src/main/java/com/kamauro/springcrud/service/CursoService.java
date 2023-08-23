package com.kamauro.springcrud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import com.kamauro.springcrud.exception.RecordNotFoundException;
import com.kamauro.springcrud.model.Curso;
import com.kamauro.springcrud.repository.CursoRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Service
@Validated
public class CursoService {

    private final CursoRepository cursoRepository;
    
    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

     public List<Curso> lista() {
        return cursoRepository.findAll();
    } 

    public Curso buscarPorId(@NotNull @Positive Long id) {
        return cursoRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public Curso create(@Valid Curso curso) {
        return cursoRepository.save(curso);
    } 

    public Curso update(@NotNull @Positive Long id, @Valid Curso curso) {
        return cursoRepository.findById(id)
                    .map(record -> {
                        record.setName(curso.getName());
                        record.setCategory(curso.getCategory());                        
                        return cursoRepository.save(record);
                    }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@PathVariable @NotNull @Positive Long id) {
        cursoRepository.delete(cursoRepository.findById(id)
            .orElseThrow(() -> new RecordNotFoundException(id)));

    }
}
