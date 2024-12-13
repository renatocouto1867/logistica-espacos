package com.ntw.logistica_espacos.controller;

import com.ntw.logistica_espacos.model.entity.Espaco;
import com.ntw.logistica_espacos.model.service.EspacoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/espaco")
@Tag(name = "Espaço", description = "Métodos disponíveis para Espaço")
public class EspacoController {
    @Autowired
    private EspacoService espacoService;

    @PostMapping
    public ResponseEntity<Espaco> criarEspaco(@RequestBody Espaco espaco) {
        return ResponseEntity.ok(espacoService.criarEspaco(espaco));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Espaco> atualizarEspaco(@PathVariable Long id, @RequestBody Espaco espaco) {
        return ResponseEntity.ok(espacoService.atualizarEspaco(id, espaco));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desativarEspaco(@PathVariable Long id) {
        espacoService.desativarEspaco(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Espaco>> listarTodos() {
        return ResponseEntity.ok(espacoService.listarTodos());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Espaco> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(espacoService.buscarPorId(id));
    }


}


