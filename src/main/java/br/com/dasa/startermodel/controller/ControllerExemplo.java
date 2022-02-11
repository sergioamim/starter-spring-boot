package br.com.dasa.startermodel.controller;

import br.com.dasa.startermodel.application.ExemploApplication;
import br.com.dasa.startermodel.entity.Exemplo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @autor: Jucilene L. Mahle
 **/

@Slf4j
@RestController
@RequestMapping("/exemplo")
public class ControllerExemplo {

    @Autowired
    private ExemploApplication exemploApplication;

    @GetMapping(name = "/all")
    public ResponseEntity<List<Exemplo>> getAll() {

        return ResponseEntity.ok(exemploApplication.getAll());
    }
}
