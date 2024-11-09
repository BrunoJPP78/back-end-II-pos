package com.aula.biblioteca.model;

import com.aula.biblioteca.dto.LivroDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "livros")
public class Livro {

    @Id
    private String id; //OBS: O ID no MongoDB não é um Long, mas sim uma STRING.
    private String titulo;
    private String descricao;
    private Integer qtdePaginas;
    private Float nota;

    @DBRef
    @JsonIgnore
    private List<Autor> autores;

    public static Livro fromDto(LivroDto livroDto){
        return new Livro(null, livroDto.titulo(), livroDto.descricao(),
                livroDto.qtdePaginas(), livroDto.nota(), livroDto.autores());
    }
}

