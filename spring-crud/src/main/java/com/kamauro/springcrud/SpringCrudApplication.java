package com.kamauro.springcrud;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.kamauro.springcrud.enums.Category;
import com.kamauro.springcrud.model.Aula;
import com.kamauro.springcrud.model.Curso;
import com.kamauro.springcrud.repository.CursoRepository;

@SpringBootApplication
public class SpringCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCrudApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(CursoRepository cursoRepository) {
		return args -> {
			cursoRepository.deleteAll();
			Curso c = new Curso();
			c.setName("Angular com Spring");
			c.setCategory(Category.FRONT_END);
			
			Aula a = new Aula();
			a.setName("Introdução");
			a.setUrlYoutube("watch?v=1");
			a.setCurso(c);
			c.getAulas().add(a);

			Aula a1 = new Aula();
			a1.setName("Angular");
			a1.setUrlYoutube("watch?v=2");
			a1.setCurso(c);

			c.getAulas().add(a1);
			cursoRepository.save(c);

		};
	}

}
