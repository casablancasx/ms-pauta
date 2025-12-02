package br.gov.agu.nutec.mspauta.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoContestacao {
    TIPO1("TIPO 1"),
    TIPO2("TIPO 2"),
    TIPO3("TIPO 3"),
    TIPO4("TIPO 4"),
    TIPO5("TIPO 5"),
    SEM_CONTESTACAO("SEM CONTESTAÇÃO"),
    SEM_TIPO("SEM TIPO");

    private String descricao;

}
