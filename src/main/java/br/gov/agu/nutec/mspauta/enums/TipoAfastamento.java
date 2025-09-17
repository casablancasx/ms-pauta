package br.gov.agu.nutec.mspauta.enums;

import lombok.Getter;

@Getter
public enum TipoAfastamento {
    FERIAS(1, "Férias"),
    ATESTADO_MEDICO(2, "Atestado Médico"),
    LICENCA(3, "Licença");

    private final int codigo;
    private final String descricao;

    TipoAfastamento(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }
}
