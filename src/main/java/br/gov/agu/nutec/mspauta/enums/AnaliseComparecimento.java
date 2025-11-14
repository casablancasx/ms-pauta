package br.gov.agu.nutec.mspauta.enums;

import lombok.Getter;

@Getter
public enum AnaliseComparecimento {

    COMPARECIMENTO("Comparecimento"),
    NAO_COMPARECIMENTO("Não Comparecimento"),
    ANALISE_PENDENTE("Análise Pendente");

    private String descricao;

    AnaliseComparecimento(String descricao) {
        this.descricao = descricao;
    }
}
