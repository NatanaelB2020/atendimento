package com.suporte.atendimento.controller;

import com.suporte.atendimento.entity.Atendimento;
import com.suporte.atendimento.service.AtendimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/atendimentos")
public class AtendimentoController {
    private final AtendimentoService atendimentoService;

    @Autowired
    public AtendimentoController(AtendimentoService atendimentoService) {
        this.atendimentoService = atendimentoService;
    }

    @GetMapping
    public ResponseEntity<List<Atendimento>> getAllAtendimentos() {
        List<Atendimento> atendimentos = atendimentoService.getAllAtendimentos();
        return ResponseEntity.ok(atendimentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Atendimento> getAtendimentoById(@PathVariable Long id) {
        Optional<Atendimento> atendimentoOptional = atendimentoService.getAtendimentoById(id);
        return atendimentoOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<Atendimento> createAtendimento(@RequestBody Atendimento atendimento) {
        Atendimento createdAtendimento = atendimentoService.createAtendimento(atendimento);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAtendimento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Atendimento> updateAtendimento(
            @PathVariable Long id,
            @RequestBody Atendimento updatedAtendimento
    ) {
        Atendimento updated = atendimentoService.updateAtendimento(id, updatedAtendimento);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAtendimento(@PathVariable Long id) {
        atendimentoService.deleteAtendimento(id);
        return ResponseEntity.noContent().build();
    }
}
