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
@RequestMapping("/agentes")
public class AgenteAtendimentoController {

    private final AgenteAtendimentoService agenteAtendimentoService;

    @Autowired
    public AgenteAtendimentoController(AgenteAtendimentoService agenteAtendimentoService) {
        this.agenteAtendimentoService = agenteAtendimentoService;
    }

    @GetMapping("/getall")
    public ResponseEntity<List<AgenteAtendimento>> getAllAgentes() {
        List<AgenteAtendimento> agentes = agenteAtendimentoService.getAllAgentes();
        return new ResponseEntity<>(agentes, HttpStatus.OK);
    }

    @GetMapping("/getByid/{id}")
    public ResponseEntity<AgenteAtendimento> getAgenteById(@PathVariable Long id) {
        Optional<AgenteAtendimento> agente = agenteAtendimentoService.getAgenteById(id);
        return agente.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AgenteAtendimento> updateAgente(@PathVariable Long id, @RequestBody AgenteAtendimento agente) {
        Optional<AgenteAtendimento> updatedAgente = agenteAtendimentoService.updateAgente(id, agente);
        return updatedAgente.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAgente(@PathVariable Long id) {
        boolean deleted = agenteAtendimentoService.deleteAgente(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
