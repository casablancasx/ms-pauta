package br.gov.agu.nutec.mspauta.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_pautista")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PautistaEntity extends EntidadeSapiens {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pautista_id")
    private Integer pautistaId;

    private boolean disponivel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adicionado_por_id")
    private UsuarioEntity adicionadoPor;
}
