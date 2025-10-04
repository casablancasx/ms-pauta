package br.gov.agu.nutec.mspauta.dto;

import br.gov.agu.nutec.mspauta.entity.AdvogadoEntity;
import br.gov.agu.nutec.mspauta.entity.UfEntity;
import br.gov.agu.nutec.mspauta.enums.Uf;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * DTO de saída para informações de Advogado.
 */
@Data
@Builder
@AllArgsConstructor
public class AdvogadoDTO {
    private Long id;
    private String nome;
    private boolean prioritario;
    private List<Uf> ufs;

    public static AdvogadoDTO fromEntity(AdvogadoEntity entity) {
        List<Uf> ufs = entity.getUfs() == null ? List.of() : entity.getUfs().stream()
                .filter(Objects::nonNull)
                .map(UfEntity::getSigla)
                .collect(Collectors.toList());

        return AdvogadoDTO.builder()
                .id(entity.getAdvogadoId())
                .nome(entity.getNome())
                .prioritario(entity.isPrioritario())
                .ufs(ufs)
                .build();
    }
}
