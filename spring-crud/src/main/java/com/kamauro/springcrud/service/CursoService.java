package com.kamauro.springcrud.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import com.kamauro.springcrud.dto.CursoDTO;
import com.kamauro.springcrud.dto.mapper.CursoMapper;
import com.kamauro.springcrud.exception.RecordNotFoundException;
import com.kamauro.springcrud.repository.CursoRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Service
@Validated
public class CursoService {

    private final CursoRepository cursoRepository;
    private final CursoMapper cursoMapper;
    
    public CursoService(CursoRepository cursoRepository, CursoMapper cursoMapper) {
        this.cursoRepository = cursoRepository;
        this.cursoMapper = cursoMapper;
    }

     public List<CursoDTO> lista() {
       return cursoRepository.findAll()
                .stream()
                .map(cursoMapper::toDto)
                .collect(Collectors.toList());
    } 

    public CursoDTO buscarPorId(@NotNull @Positive Long id) {
        return cursoRepository.findById(id)
                .map(cursoMapper::toDto)  
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public CursoDTO create(@Valid @NotNull CursoDTO cursoDTO) {
        return cursoMapper.toDto(cursoRepository.save(cursoMapper.toEntity(cursoDTO)));
    } 

    public CursoDTO update(@NotNull @Positive Long id, @Valid @NotNull CursoDTO cursoDTO) {
        return cursoRepository.findById(id)
                    .map(record -> {
                        record.setName(cursoDTO.name());
                        record.setCategory(cursoDTO.category());                        
                        return cursoMapper.toDto(cursoRepository.save(record));
                    }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@PathVariable @NotNull @Positive Long id) {
        cursoRepository.delete(cursoRepository.findById(id)
            .orElseThrow(() -> new RecordNotFoundException(id)));

    }
}
