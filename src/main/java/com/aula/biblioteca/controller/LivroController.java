package com.aula.biblioteca.controller;

import com.aula.biblioteca.dto.LivroDto;
import com.aula.biblioteca.exception.ResponseMessageException;
import com.aula.biblioteca.repository.LivroRepository;
import com.aula.biblioteca.service.LivroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/livros",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class LivroController {

    private final LivroService livroService;

    @Operation(description = "Listar todos os livros da base de dados", summary = "Listar livros")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorno de livros",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class))),
    })
    @GetMapping
    public ResponseEntity<Page<LivroDto>> findAll(@PageableDefault(size = 5) Pageable pagination){
        return ResponseEntity.ok(livroService.findAll(pagination));
    }

    @Operation(description = "Listar um livro específico da base de dados", summary = "Listar livro específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "20", description = "Livro específico listado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class))),
    })
    @GetMapping("/{id}")
    public ResponseEntity<LivroDto>findById(@PathVariable String id) {
        return ResponseEntity.ok(livroService.findById(id));
    }

    @Operation(description = "Criar um novo livro na base de dados", summary = "Criar livro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Livro criado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class))),
    })
    @PostMapping
    public ResponseEntity<LivroDto> save(@RequestBody @Valid LivroDto livroDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(livroService.save(livroDto));
    }

}
