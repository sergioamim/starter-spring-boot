package br.com.dasa.startermodel.controller;

import br.com.dasa.startermodel.application.ExemploApplication;
import br.com.dasa.startermodel.entity.Exemplo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exemplo")
public class ControllerExemplo {

    @Autowired
    private ExemploApplication exemploApplication;

    @GetMapping("/all")
    public ResponseEntity<List<Exemplo>> getAll() {

        return ResponseEntity.ok(exemploApplication.getAll());
    }
}
