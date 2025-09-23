package br.gov.agu.nutec.mspauta.enums;

public enum StatusCadastro {
    CADASTRO_PENDETE("Cadastro Pendente"),
    CADASTRO_SUCESSO("Cadastrado com sucesso"),
    CADASTRO_FALHA("Falha no cadastro");

    private String descricao;

    StatusCadastro(String descricao) {
        this.descricao = descricao;
    }
}
