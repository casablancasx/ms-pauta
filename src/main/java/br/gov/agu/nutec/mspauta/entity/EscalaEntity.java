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

    @OneToOne
    @JoinColumn(name = "pauta_id")
    private PautaEntity pauta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pautista_id", referencedColumnName = "sapiens_id")
    private PautistaEntity pautista;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "avaliador_id", referencedColumnName = "sapiens_id")
    private AvaliadorEntity avaliador;

    @Column(name = "criado_em")
    private LocalDateTime criadoEm;
}
