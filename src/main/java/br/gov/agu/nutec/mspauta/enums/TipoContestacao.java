package br.gov.agu.nutec.mspauta.enums;

public enum TipoContestacao {
    TIPO1("TIPO1"),
    TIPO2("TIPO2"),
    TIPO3("TIPO3"),
    TIPO4("TIPO4"),
    TIPO5("TIPO5"),
    SEM_CONSTESTACAO("SEM CONSTESTAÇÃO"),
    SEM_TIPO("SEM TIPO");

    private String descricao;

    TipoContestacao(String descricao){
        this.descricao = descricao;
    }
    public String getDescricao() {
        return descricao;
    }
}
