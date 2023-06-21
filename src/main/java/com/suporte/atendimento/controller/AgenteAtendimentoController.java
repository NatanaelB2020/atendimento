package com.suporte.atendimento.controller;

import com.suporte.atendimento.entity.AgenteAtendimento;
import com.suporte.atendimento.service.AgenteAtendimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/agentes-atendimento")
public class AgenteAtendimentoController {
    private final AgenteAtendimentoService agenteAtendimentoService;

    @Autowired
    public AgenteAtendimentoController(AgenteAtendimentoService agenteAtendimentoService) {
        this.agenteAtendimentoService = agenteAtendimentoService;
    }

    @GetMapping
    public ResponseEntity<List<AgenteAtendimento>> getAllAgentesAtendimento() {
        List<AgenteAtendimento> agentesAtendimento = agenteAtendimentoService.getAllAgentesAtendimento();
        return ResponseEntity.ok(agentesAtendimento);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgenteAtendimento> getAgenteAtendimentoById(@PathVariable Long id) {
        Optional<AgenteAtendimento> agenteAtendimentoOptional = agenteAtendimentoService.getAgenteAtendimentoById(id);
        return agenteAtendimentoOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<AgenteAtendimento> createAgenteAtendimento(@RequestBody AgenteAtendimento agenteAtendimento) {
        AgenteAtendimento createdAgenteAtendimento = agenteAtendimentoService.createAgenteAtendimento(agenteAtendimento);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAgenteAtendimento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgenteAtendimento> updateAgenteAtendimento(
            @PathVariable Long id,
            @RequestBody AgenteAtendimento updatedAgenteAtendimento
    ) {
        AgenteAtendimento agenteAtendimento = agenteAtendimentoService.updateAgenteAtendimento(id, updatedAgenteAtendimento);
        return ResponseEntity.ok(agenteAtendimento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAgenteAtendimento(@PathVariable Long id) {
        agenteAtendimentoService.deleteAgenteAtendimento(id);
        return ResponseEntity.noContent().build();
    }
}
