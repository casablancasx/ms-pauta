package br.gov.agu.nutec.mspauta.dto.request;

import br.gov.agu.nutec.mspauta.enums.Uf;

import java.util.List;

public record AdvogadoRequestDTO(
        String nome,
        List<Uf> ufs
) {
}
