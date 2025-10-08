package br.gov.agu.nutec.mspauta.dto.request;

import br.gov.agu.nutec.mspauta.enums.StatusAnaliseComparecimento;

public record PautaUpdateDTO(
    Long pautaId,
    StatusAnaliseComparecimento analiseComparecimento
) {
}
