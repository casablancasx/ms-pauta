package br.gov.agu.nutec.mspauta.enums;

import lombok.Getter;

@Getter
public enum StatusAnalise {

    COMPARECIMENTO("Comparecimento"),
    NAO_COMPARECIMENTO("Não Comparecimento"),
    ANALISE_PENDENTE("Análise Pendente");

    private String descricao;

    StatusAnalise(String descricao) {
        this.descricao = descricao;
    }
}
