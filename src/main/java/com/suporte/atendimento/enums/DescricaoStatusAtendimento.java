package com.suporte.atendimento.enums;

public enum DescricaoStatusAtendimento {
    EM_ATENDIMENTO("Em Atendimento"),
    AGUARDANDO_SOLUCAO("Aguardando Solução"),
    AGUARDANDO_TESTES("Aguardando Testes"),
    PENDENTE("Pendente"),
    RESOLVIDO("Resolvido");

    private final String descricao;

    DescricaoStatusAtendimento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
