package br.edu.infnet.mono.domain.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/teste")
public class TesteController {

    @GetMapping
    public ResponseEntity<String> teste() {
        return ResponseEntity.ok("Teste rest com sucesso!");
    }
}
