package br.gov.agu.nutec.mspauta.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoContestacao {
    TIPO1("TIPO1"),
    TIPO2("TIPO2"),
    TIPO3("TIPO3"),
    TIPO4("TIPO4"),
    TIPO5("TIPO5"),
    SEM_CONTESTACAO("SEM CONTESTAÇÃO"),
    SEM_TIPO("SEM TIPO");

    private String descricao;

}
