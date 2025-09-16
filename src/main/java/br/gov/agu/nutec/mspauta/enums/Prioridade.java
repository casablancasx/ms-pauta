package br.gov.agu.nutec.mspauta.enums;

public enum Prioridade {

    ALTA("ALTA"),
    NORMAL("NORMAL");

    private String descricao;

    Prioridade(String descricao) {
        this.descricao = descricao;
    }
}
