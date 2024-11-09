package com.aula.biblioteca;

import com.aula.biblioteca.repository.LivroRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BibliotecaApplication {

	@Autowired
	private LivroRepository livroRepository;

	public static void main(String[] args) {
		SpringApplication.run(BibliotecaApplication.class, args);
	}


	@PostConstruct
	public void MongoDBTest(){
		// System.out.println(livroRepository.findByTitulo("Livro B"));
		// System.out.println(livroRepository.findByNotaGreaterThanEqual(9F));
		// System.out.println(livroRepository.findByNotaGTEAndQtePaginasGTE(9F, 200));
	}
}
