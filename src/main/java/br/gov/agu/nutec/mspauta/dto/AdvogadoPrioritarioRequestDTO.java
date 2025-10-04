package br.gov.agu.nutec.mspauta.dto;

import br.gov.agu.nutec.mspauta.enums.Uf;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO de entrada para cadastro de Advogado prioritário (suspeita de fraude).
 * Contém apenas nome e a lista de UFs onde atua.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdvogadoPrioritarioRequestDTO {

    private String nome;

    private List<Uf> ufs;
}
