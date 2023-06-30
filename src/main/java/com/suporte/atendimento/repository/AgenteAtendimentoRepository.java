package com.suporte.atendimento.repository;

import com.suporte.atendimento.entity.AgenteAtendimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgenteAtendimentoRepository extends JpaRepository<AgenteAtendimento, Long> {
}
