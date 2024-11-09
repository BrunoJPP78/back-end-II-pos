package com.aula.biblioteca.controller;

import com.aula.biblioteca.dto.AutorDto;
import com.aula.biblioteca.service.AutorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/autores")
public class AutorController {

    private final AutorService autorService;

    @Operation(description = "Listar todo os autores com paginação da base de dados", summary = "Listar todos os Autores") //Documentação Swagger
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorno de autores",
                    content = @Content(mediaType = "application/json", schema=@Schema(implementation = Page.class)))
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<AutorDto>> findAll(@Parameter(required = true) @PageableDefault Pageable pageable){
        return ResponseEntity.ok(autorService.findAll(pageable));
    }

    @Operation(description = "Criar um novo autor na base de dados", summary = "Criar autor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Autor criado com sucesso",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class))),
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AutorDto> save (@RequestBody AutorDto autorDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(autorService.save(autorDto));
    }

    @Operation(description = "Atualizar um autor da base de dados", summary = "Atualizar autor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Autor atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class))),
    })
    @PostMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AutorDto> update(@PathVariable String id, @RequestBody AutorDto autorDto){
        return ResponseEntity.ok(autorService.update(id, autorDto));
    }
}
