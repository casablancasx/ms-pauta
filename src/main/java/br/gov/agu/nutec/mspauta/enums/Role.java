package br.gov.agu.nutec.mspauta.enums;

public enum Role {
    ADMIN("ADMIN"),
    USER("USER");

    private final String descricao;

    Role(String descricao) {
        this.descricao = descricao;
    }

}
