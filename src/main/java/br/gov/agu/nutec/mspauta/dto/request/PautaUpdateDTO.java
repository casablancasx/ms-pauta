package br.gov.agu.nutec.mspauta.dto.request;

import br.gov.agu.nutec.mspauta.enums.AnaliseComparecimento;

public record PautaUpdateDTO(
    Long pautaId,
    AnaliseComparecimento analiseComparecimento
) {
}
