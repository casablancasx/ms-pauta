package br.gov.agu.nutec.mspauta.dto.request;

import br.gov.agu.nutec.mspauta.enums.AnaliseComparecimento;

public record AudienciaUpdateDTO(
        Long audienciaId,
        AnaliseComparecimento analiseComparecimento,
        String observacao
) {
}
