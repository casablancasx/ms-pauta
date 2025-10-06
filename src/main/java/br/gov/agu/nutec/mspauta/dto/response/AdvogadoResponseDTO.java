package br.gov.agu.nutec.mspauta.dto.response;

import br.gov.agu.nutec.mspauta.enums.Uf;

import java.util.List;

public record AdvogadoResponseDTO(
        Long id,
        String nome,
        List<Uf> ufs,
        boolean isPrioritario
) {
}
