package com.suporte.atendimento.dtos;

import com.suporte.atendimento.enums.Contrato;
import com.suporte.atendimento.enums.Entrada;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AtendimentoDTO {
    private Long agenteAtendimentoId;
    private Contrato contrato;
    private Entrada entrada;
    private String cliente;
    private String municipioEstado;
    private String motivo;
    private String tempo;
    private String quantidade;
    private String email;


}
