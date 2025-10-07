package br.gov.agu.nutec.mspauta.enums;

import lombok.Getter;

@Getter
public enum StatusAnaliseComparecimento {

    COMPARECIMENTO("Comparecimento"),
    NAO_COMPARECIMENTO("Não Comparecimento"),
    ANALISE_PENDENTE("Análise Pendente");

    private String descricao;

    StatusAnaliseComparecimento(String descricao) {
        this.descricao = descricao;
    }
}
