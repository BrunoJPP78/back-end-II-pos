package com.aula.biblioteca.dto;

import com.aula.biblioteca.model.Autor;
import com.aula.biblioteca.model.Livro;

import java.util.List;

public record AutorDto(
        String id,
        String nome,
        String email,
        List<Livro> livros
) {
    public  AutorDto(Autor autor){
        this(autor.getId(), autor.getNome(), autor.getEmail(), autor.getLivros());
    }
}
