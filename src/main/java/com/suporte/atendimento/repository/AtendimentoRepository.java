package com.suporte.atendimento.repository;

import com.suporte.atendimento.entity.Atendimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {
}
