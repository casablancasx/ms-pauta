package br.gov.agu.nutec.mspauta.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tb_advogados")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdvogadoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "advogado_seq")
    @SequenceGenerator(name = "advogado_seq", sequenceName = "tb_advogados_advogado_id_seq", allocationSize = 1)
    @Column(name = "advogado_id", nullable = false)
    private Long advogadoId;

    private String nome;

    @Column(name = "is_suspeito")
    private boolean isSuspeito;

    @ManyToMany(mappedBy = "advogados")
    private List<AudienciaEntity> audiencias;
}
