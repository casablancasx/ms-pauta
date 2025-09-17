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

    @ManyToOne
    @JoinColumn(name = "pauta_id")
    private PautaEntity pauta;

    @ManyToOne
    @JoinColumn(name = "pautista_id")
    private PautistaEntity pautista;

    @ManyToOne
    @JoinColumn(name = "avaliador_id")
    private AvaliadorEntity avaliador;

    private LocalDateTime criadoEm;

    @PrePersist
    protected void onCreate() {
        this.criadoEm = LocalDateTime.now();
    }
}
