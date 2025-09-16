package br.gov.agu.nutec.mspauta.dto;

import br.gov.agu.nutec.mspauta.enums.Turno;

import java.time.LocalDate;

public record PautaDTO(

        LocalDate data,
        String orgaoJulgador,
        String sala,
        Turno turno
) {
}
