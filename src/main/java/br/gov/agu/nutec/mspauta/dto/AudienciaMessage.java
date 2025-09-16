package br.gov.agu.nutec.mspauta.dto;


import br.gov.agu.nutec.mspauta.enums.Status;

public record AudienciaMessage(
        Status status,
        AudienciaDTO audiencia
) {
}
