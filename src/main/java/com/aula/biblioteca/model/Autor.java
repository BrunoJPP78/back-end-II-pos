package com.aula.biblioteca.model;

import com.aula.biblioteca.dto.AutorDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "autores")
public class Autor {

    @Id
    private String id;
    private String nome;
    private String email;

    @JsonIgnore //Vai ignorar a consulta de livros dentro de autores quando fizer o get pra autores
    @DBRef
    private List<Livro> livros;

    public static Autor fromDto(AutorDto autorDto){
        return new Autor(null, autorDto.nome(), autorDto.email(), autorDto.livros());
    }
}
