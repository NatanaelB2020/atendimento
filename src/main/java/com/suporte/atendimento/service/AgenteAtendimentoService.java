package com.suporte.atendimento.service;

import com.suporte.atendimento.entity.AgenteAtendimento;
import com.suporte.atendimento.repository.AgenteAtendimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgenteAtendimentoService {

    private final AgenteAtendimentoRepository agenteAtendimentoRepository;

    @Autowired
    public AgenteAtendimentoService(AgenteAtendimentoRepository agenteAtendimentoRepository) {
        this.agenteAtendimentoRepository = agenteAtendimentoRepository;
    }

    public List<AgenteAtendimento> getAllAgentes() {
        return agenteAtendimentoRepository.findAll();
    }

    public Optional<AgenteAtendimento> getAgenteById(Long id) {
        return agenteAtendimentoRepository.findById(id);
    }

    public AgenteAtendimento createAgenteAtendimento(AgenteAtendimento agenteAtendimento) {
        return agenteAtendimentoRepository.save(agenteAtendimento);
    }

    public Optional<AgenteAtendimento> updateAgente(Long id, AgenteAtendimento agente) {
        Optional<AgenteAtendimento> existingAgente = agenteAtendimentoRepository.findById(id);
        existingAgente.ifPresent(a -> {
            a.setUser(agente.getUser());
            a.setAtendimentos(agente.getAtendimentos());
            agenteAtendimentoRepository.save(a);
        });
        return existingAgente;
    }

    public boolean deleteAgente(Long id) {
        if (agenteAtendimentoRepository.existsById(id)) {
            agenteAtendimentoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
