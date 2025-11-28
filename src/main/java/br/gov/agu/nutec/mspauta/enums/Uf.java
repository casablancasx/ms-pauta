package br.gov.agu.nutec.mspauta.enums;

import java.util.Arrays;

public enum Uf {

    AC("AC"),
    AL("AL"),
    AP("AP"),
    AM("AM"),
    BA("BA"),
    CE("CE"),
    DF("DF"),
    ES("ES"),
    GO("GO"),
    MA("MA"),
    MT("MT"),
    MS("MS"),
    MG("MG"),
    PA("PA"),
    PB("PB"),
    PR("PR"),
    PE("PE"),
    PI("PI"),
    RJ("RJ"),
    RN("RN"),
    RS("RS"),
    RO("RO"),
    RR("RR"),
    SC("SC"),
    SP("SP"),
    SE("SE"),
    TO("TO");

    private final String sigla;

    Uf(String sigla) {
        this.sigla = sigla;
    }

    public String getSigla() {
        return sigla;
    }

    @Override
    public String toString() {
        return sigla;
    }

    /**
     * Retorna a UF correspondente à sigla (case-insensitive).
     * Lança IllegalArgumentException se não existir.
     */
    public static Uf fromSigla(String sigla) {
        return Arrays.stream(values())
                .filter(uf -> uf.sigla.equalsIgnoreCase(sigla))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("UF inválida: " + sigla));
    }

    /**
     * Valida se a sigla informada existe.
     */
    public static boolean isValid(String sigla) {
        return Arrays.stream(values())
                .anyMatch(uf -> uf.sigla.equalsIgnoreCase(sigla));
    }

    /**
     * Retorna todas as siglas (útil para exibir em API).
     */
    public static String[] siglas() {
        return Arrays.stream(values())
                .map(Uf::getSigla)
                .toArray(String[]::new);
    }
}
