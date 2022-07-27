package br.com.dasa.startermodel.controller;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import br.com.dasa.startermodel.controller.dto.ExemploDTO;
import br.com.dasa.startermodel.controller.mapper.ExemploMapper;
import br.com.dasa.startermodel.entity.Exemplo;
import br.com.dasa.startermodel.service.ExemploService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("v1/exemplo")
public class ExemploController {

    @Autowired
    private ExemploService service;

    @Autowired
    private ExemploMapper mapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Localiza todos exemplos")
    public List<ExemploDTO> findAll() {
        return service.findAll()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @Operation(summary = "Localiza exemplo por id")
    public ExemploDTO findById(@PathVariable Long id) {
        return ExemploMapper.INSTANCE.toDTO(service.findById(id));
//        return Optional.of(id)
//                .map(service::findById)
//                .map(mapper::toDTO)
//                .map( exemplo -> ResponseEntity.ok().body(exemplo) )          //200 OK
//                .orElseThrow(() -> new NotFoundBusinessException("Exemplo não encontrado"));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable Long id, @RequestBody ExemploDTO request) {
        Exemplo exemplo = mapper.toEntity(request);
        service.update(exemplo);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Exemplo create(@RequestBody ExemploDTO request) {
        Exemplo exemplo = mapper.toEntity(request);
        return service.create(exemplo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
