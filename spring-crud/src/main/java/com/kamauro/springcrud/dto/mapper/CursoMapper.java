package com.kamauro.springcrud.dto.mapper;

import org.springframework.stereotype.Component;

import com.kamauro.springcrud.dto.CursoDTO;
import com.kamauro.springcrud.model.Curso;

@Component
public class CursoMapper {
    
    public CursoDTO toDto(Curso curso) {
        if(curso == null) {
            return null;
        }
        return new CursoDTO(curso.getId(), curso.getName(), curso.getCategory());
    }

     public Curso toEntity(CursoDTO cursoDTO) {
        if(cursoDTO == null) {
            return null;
        }

        Curso curso = new Curso();
        if(cursoDTO.id() != null) {
            curso.setId(null);
        }
        curso.setName(cursoDTO.name());
        curso.setCategory(cursoDTO.category());
        curso.setStatus("Ativo");
        return curso;
    }

    //Verificar padrao builder para entidade de varios elementos
}
