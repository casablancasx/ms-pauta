package br.gov.agu.nutec.mspauta.enums;

public enum TipoContestacao {
    TIPO1(1,"TIPO1"),
    TIPO2(2,"TIPO2"),
    TIPO3(3,"TIPO3"),
    TIPO4(4,"TIPO4"),
    TIPO5(5,"TIPO5"),
    BRANCO(0,"EM BRANCO");

    private int codigo;
    private String descricao;

    TipoContestacao(int codigo, String descricao){
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public static TipoContestacao getTipoContestacao(Integer codigo){
        for (TipoContestacao tipo : TipoContestacao.values()){
            if(tipo.codigo == codigo)
                return tipo;
        }
        return BRANCO;
    }
}
