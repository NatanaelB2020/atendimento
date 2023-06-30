package com.suporte.atendimento.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "user") // Excluir o relacionamento "user" do toString para evitar um ciclo infinito
public class AgenteAtendimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "agenteAtendimento")
    private List<Atendimento> atendimentos;

    @Column(name = "nome")
    private String nome;

    // Constructors, getters, and setters

    public void setNome() {
        if (user != null) {
            this.nome = user.getUsername();
        }
    }
}
