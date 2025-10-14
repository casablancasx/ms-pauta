package br.gov.agu.nutec.mspauta.dto.request;

import br.gov.agu.nutec.mspauta.enums.StatusAnaliseComparecimento;

public record AudienciaUpdateDTO(
        Long audienciaId,
        StatusAnaliseComparecimento analiseComparecimento,
        String observacao
) {
}
