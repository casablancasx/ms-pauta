package br.gov.agu.nutec.mspauta.enums;

public enum Status {
    EM_ANDAMENTO("EM ANDAMENTO"),
    FINALIZADO("FINALIZADO");

    private final String descricao;

    Status(String descricao) {
        this.descricao = descricao;
    }
}
