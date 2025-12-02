package br.gov.agu.nutec.mspauta.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum AnaliseComparecimento {

    COMPARECIMENTO("Comparecimento"),
    NAO_COMPARECIMENTO("Não Comparecimento"),
    ANALISE_PENDENTE("Análise Pendente"),
    CANCELADA("Cancelada"),
    REDESIGNADA("Redesignada");

    private String descricao;

    AnaliseComparecimento(String descricao) {
        this.descricao = descricao;
    }

    @JsonValue
    public String getDescricao() {
        return descricao;
    }

    @JsonCreator
    public static AnaliseComparecimento fromDescricao(String value) {
        if (value == null) {
            return null;
        }
        
        // Try to match by description first
        for (AnaliseComparecimento status : AnaliseComparecimento.values()) {
            if (status.descricao.equalsIgnoreCase(value)) {
                return status;
            }
        }
        
        // Try to match by enum name as fallback
        try {
            return AnaliseComparecimento.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Valor inválido para AnaliseComparecimento: " + value);
        }
    }
}
