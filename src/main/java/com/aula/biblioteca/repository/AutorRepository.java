package com.aula.biblioteca.repository;

import com.aula.biblioteca.model.Autor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepository extends MongoRepository<Autor, String> {
}
