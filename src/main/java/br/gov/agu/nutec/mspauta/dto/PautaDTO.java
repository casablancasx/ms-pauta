package br.gov.agu.nutec.mspauta.dto;

import br.gov.agu.nutec.mspauta.enums.Turno;
import br.gov.agu.nutec.mspauta.enums.Uf;

import java.time.LocalDate;

public record PautaDTO(

        LocalDate data,
        String orgaoJulgador,
        String sala,
        Turno turno,
        Uf uf
) {
}
