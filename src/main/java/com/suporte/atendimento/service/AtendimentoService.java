package com.suporte.atendimento.service;

import com.suporte.atendimento.entity.Atendimento;
import com.suporte.atendimento.repository.AtendimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AtendimentoService {
    private final AtendimentoRepository atendimentoRepository;

    @Autowired
    public AtendimentoService(AtendimentoRepository atendimentoRepository) {
        this.atendimentoRepository = atendimentoRepository;
    }

    public List<Atendimento> getAllAtendimentos() {
        return atendimentoRepository.findAll();
    }

    public Optional<Atendimento> getAtendimentoById(Long id) {
        return atendimentoRepository.findById(id);
    }

    public Atendimento createAtendimento(Atendimento atendimento) {
        return atendimentoRepository.save(atendimento);
    }

    public Atendimento updateAtendimento(Long id, Atendimento updatedAtendimento) {
        Optional<Atendimento> existingAtendimento = atendimentoRepository.findById(id);
        if (existingAtendimento.isPresent()) {
            Atendimento atendimento = existingAtendimento.get();
            atendimento.setAgenteAtendimento(updatedAtendimento.getAgenteAtendimento());
            atendimento.setStatusAtendimento(updatedAtendimento.getStatusAtendimento());
            atendimento.setData(updatedAtendimento.getData());
            atendimento.setCliente(updatedAtendimento.getCliente());
            atendimento.setMunicipioEstado(updatedAtendimento.getMunicipioEstado());
            atendimento.setContrato(updatedAtendimento.getContrato());
            atendimento.setMotivo(updatedAtendimento.getMotivo());
            atendimento.setTempo(updatedAtendimento.getTempo());
            atendimento.setQuantidade(updatedAtendimento.getQuantidade());
            atendimento.setEntrada(updatedAtendimento.getEntrada());
            atendimento.setEmail(updatedAtendimento.getEmail());
            return atendimentoRepository.save(atendimento);
        } else {
            return null; // ou pode lançar uma exceção, se preferir
        }
    }

    public void deleteAtendimento(Long id) {
        atendimentoRepository.deleteById(id);
    }
}
