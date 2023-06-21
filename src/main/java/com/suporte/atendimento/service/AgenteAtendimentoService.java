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


    public List<AgenteAtendimento> getAllAgentesAtendimento() {
        return agenteAtendimentoRepository.findAll();
    }

    public Optional<AgenteAtendimento> getAgenteAtendimentoById(Long id) {
        return agenteAtendimentoRepository.findById(id);
    }

    public AgenteAtendimento createAgenteAtendimento(AgenteAtendimento agenteAtendimento) {
        return agenteAtendimentoRepository.save(agenteAtendimento);
    }

    public AgenteAtendimento updateAgenteAtendimento(Long id, AgenteAtendimento updateAgenteAtendimento) {
        Optional<AgenteAtendimento> existingAgenteAtendimentoOptional = agenteAtendimentoRepository.findById(id);

        if (existingAgenteAtendimentoOptional.isPresent()) {
            AgenteAtendimento existingAgenteAtendimento = existingAgenteAtendimentoOptional.get();
            existingAgenteAtendimento.setUser(updateAgenteAtendimento.getUser());
            existingAgenteAtendimento.setAtendimentos(updateAgenteAtendimento.getAtendimentos());

            return agenteAtendimentoRepository.save(existingAgenteAtendimento);
        } else {
            throw new IllegalArgumentException("AgenteAtendimento not found with id: " + id);
        }
    }

    public void deleteAgenteAtendimento(Long id) {
        agenteAtendimentoRepository.deleteById(id);
    }
    public String getNomeAgente(Long id) {
        AgenteAtendimento agenteAtendimento = agenteAtendimentoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Agente de atendimento não encontrado"));

        return agenteAtendimento.getNome();
    }
}
