package com.kamauro.springcrud;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
			c.setCategory("front-end");
			cursoRepository.save(c);

		};
	}

}
