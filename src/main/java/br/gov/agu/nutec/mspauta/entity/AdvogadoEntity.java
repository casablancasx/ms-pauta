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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "advogado_id", nullable = false)
    private Long advogadoId;

    private String nome;

    @ManyToMany(mappedBy = "advogados")
    private List<AudienciaEntity> audiencias;
}
