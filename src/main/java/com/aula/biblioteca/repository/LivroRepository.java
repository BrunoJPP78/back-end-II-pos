package com.aula.biblioteca.repository;


import com.aula.biblioteca.model.Livro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LivroRepository extends MongoRepository<Livro, String> {

    @Query("{'titulo': ?0}") //Busca pelo titulo - ?0 = primeiro parâmetro
    Optional<Livro> findByTitulo(String titulo);

    @Query("{'nota':  {'$gte':  ?0} }")
    Page<Livro> findByNotaGreaterThanEqual(Float nota, Pageable pagination);

    @Query("{'$and': [{'nota':  {'$gte':  ?0}}, {'qtdePaginas':  {'$gte' :  ?1} }]}") // ?1 = segundo parâmetro
    Page<Livro> findByNotaGTEAndQtePaginasGTE(Float nota, Integer qtdePaginas, Pageable pagination);
}


