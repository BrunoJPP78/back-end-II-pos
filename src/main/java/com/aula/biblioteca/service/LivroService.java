package com.aula.biblioteca.service;

import com.aula.biblioteca.dto.LivroDto;
import com.aula.biblioteca.model.Livro;
import com.aula.biblioteca.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor //Cria um construtor
@Service
public class LivroService {

    private final LivroRepository livroRepository;

    public Page<LivroDto> findAll(Pageable pagination){
        return livroRepository.findAll(pagination).map(livro -> new LivroDto(livro));
    }

    public LivroDto findById(String id){
        Optional<Livro> optionalLivro = livroRepository.findById(id);
        if(optionalLivro.isPresent()){
            return new LivroDto(optionalLivro.get());
        }
        throw new NoSuchElementException("Documento de Id: " + id + " não encontrado.");
    }


    public Page<LivroDto> findByNotaGreaterThanEqual(Float nota, Pageable pagination){
        return livroRepository.findByNotaGreaterThanEqual(nota, pagination).map(LivroDto::new);
    }

    public Page<LivroDto> findByNotaGTEAndQtePaginasGTE(Float nota, Integer qtdePaginas, Pageable pagination){
        return livroRepository.findByNotaGTEAndQtePaginasGTE(nota, qtdePaginas, pagination).map(LivroDto::new);
    }
    public LivroDto findByTitulo(String titulo){
        return new LivroDto(livroRepository.findByTitulo(titulo).get());
    }

    @Transactional
    public LivroDto save(LivroDto livroDto){
        Livro livro = Livro.fromDto(livroDto);
        return new LivroDto(livroRepository.save(livro));
    }

    @Transactional
    public LivroDto update(String id, LivroDto livroDto){
        Livro livro = Livro.fromDto(livroDto);
        livro.setId(id);
        return new LivroDto(livroRepository.save(livro));
    }

    @Transactional
    public void delete(String id) {
        livroRepository.deleteById(id);
    }

}
