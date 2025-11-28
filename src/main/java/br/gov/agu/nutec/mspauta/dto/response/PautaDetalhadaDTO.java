package br.gov.agu.nutec.mspauta.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class PautaDetalhadaDTO extends PautaResponseDTO{

    private List<AudienciaResponseDTO> audiencias;
}
