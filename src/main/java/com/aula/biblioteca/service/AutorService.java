package com.aula.biblioteca.service;

import com.aula.biblioteca.dto.AutorDto;
import com.aula.biblioteca.model.Autor;
import com.aula.biblioteca.repository.AutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AutorService {

    private final AutorRepository autorRepository;

    public Page<AutorDto> findAll(Pageable pageable){
        return autorRepository.findAll(pageable).map(AutorDto::new);
    }

    public AutorDto save(AutorDto autorDto){
        return new AutorDto(autorRepository.save(Autor.fromDto(autorDto)));
    }

    public AutorDto update(String id, AutorDto autorDto){
        Autor autor = Autor.fromDto(autorDto);
        autor.setId(id);
        return new AutorDto(autorRepository.save(autor));
    }
}
