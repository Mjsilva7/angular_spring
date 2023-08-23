package com.kamauro.springcrud.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kamauro.springcrud.dto.CursoDTO;
import com.kamauro.springcrud.service.CursoService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/api/cursos")
@Validated
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        
        this.cursoService = cursoService;
    }

    @GetMapping
    public @ResponseBody List<CursoDTO> lista() {
        return cursoService.lista();
    }  
    
    @GetMapping("/{id}")
    public CursoDTO buscarPorId(@PathVariable("id") @NotNull @Positive Long id) {
        return cursoService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public CursoDTO create(@RequestBody @Valid CursoDTO cursoDTO) {
        return cursoService.create(cursoDTO);
    }  

    @PutMapping("/{id}")
    public CursoDTO update(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid CursoDTO cursoDTO) {
        return cursoService.update(id, cursoDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Positive Long id) {
        cursoService.delete(id);
    }
   
}
