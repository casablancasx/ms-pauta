package br.gov.agu.nutec.mspauta.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_escalas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EscalaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long escalaId;

    // Em ms-escala existe uma restrição de unicidade para pauta_id em tb_escalas,
    // logo aqui podemos modelar como OneToOne para facilitar a navegação a partir de Pauta.
    @OneToOne
    @JoinColumn(name = "pauta_id")
    private PautaEntity pauta;

    // Relacionamentos com entidades locais para suportar navegação
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pautista_id", referencedColumnName = "sapiens_id")
    private PautistaEntity pautista;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "avaliador_id", referencedColumnName = "sapiens_id")
    private AvaliadorEntity avaliador;

    @Column(name = "criado_em")
    private LocalDateTime criadoEm;
}
