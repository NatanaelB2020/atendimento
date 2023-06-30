package com.suporte.atendimento.entity;

import com.suporte.atendimento.enums.Contrato;
import com.suporte.atendimento.enums.DescricaoStatusAtendimento;
import com.suporte.atendimento.enums.Entrada;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Atendimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "agente_atendimento_id")
    private AgenteAtendimento agenteAtendimento;

    @Enumerated(EnumType.STRING)
    private DescricaoStatusAtendimento statusAtendimento;

    private LocalDate data;
    private String cliente;
    private String municipioEstado;
    @Enumerated(EnumType.STRING)
    private Contrato contrato;
    private String motivo;
    private String tempo;
    private String quantidade;
    @Enumerated(EnumType.STRING)
    private Entrada entrada;
    private String email;

    // Constructors, getters, and setters
}
