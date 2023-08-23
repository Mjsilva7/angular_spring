package com.kamauro.springcrud.dto.mapper;

import org.springframework.stereotype.Component;

import com.kamauro.springcrud.dto.CursoDTO;
import com.kamauro.springcrud.enums.Category;
import com.kamauro.springcrud.model.Curso;

@Component
public class CursoMapper {
    
    public CursoDTO toDto(Curso curso) {
        if(curso == null) {
            return null;
        }
        return new CursoDTO(curso.getId(), curso.getName(), curso.getCategory().getValue());
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
        curso.setCategory(convertCategoryValues(cursoDTO.category()));
        return curso;
    }

    public Category convertCategoryValues(String value) {
        if(value == null) {
            return null;
        }
        return switch(value) {
            case "Back-end" -> Category.BACK_END;            
            case "Front-end" -> Category.FRONT_END;
            default -> throw new IllegalArgumentException("Categoria inválida: " + value); 

        };
    }

    //Verificar padrao builder para entidade de varios elementos
}
