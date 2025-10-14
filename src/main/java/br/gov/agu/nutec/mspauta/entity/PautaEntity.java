package br.gov.agu.nutec.mspauta.entity;


import br.gov.agu.nutec.mspauta.enums.StatusAnaliseComparecimento;
import br.gov.agu.nutec.mspauta.enums.StatusEscalaPauta;
import br.gov.agu.nutec.mspauta.enums.Turno;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tb_pautas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PautaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pautaId;

    private LocalDate data;

    @Enumerated(EnumType.STRING)
    private Turno turno;

    @Column(name = "status_escala_avaliador")
    @Enumerated(EnumType.STRING)
    private StatusEscalaPauta statusEscalaAvaliador;

    @Column(name = "status_escala_pautista")
    @Enumerated(EnumType.STRING)
    private StatusEscalaPauta statusEscalaPautista;

    @Column(name = "status_analise_comparecimento")
    @Enumerated(EnumType.STRING)
    private StatusAnaliseComparecimento analiseComparecimento;

    @ManyToOne
    @JoinColumn(name = "orgao_julgador_id")
    private OrgaoJulgadorEntity orgaoJulgador;

    @ManyToOne
    @JoinColumn(name = "sala_id")
    private SalaEntity sala;

    @OneToMany(mappedBy = "pauta")
    private List<AudienciaEntity> audiencias;

    // Referência à escala desta pauta (há no máximo 1 escala por pauta)
    @OneToOne(mappedBy = "pauta", fetch = FetchType.LAZY)
    private EscalaEntity escala;

    public PautaEntity(LocalDate data, Turno turno, StatusEscalaPauta statusEscalaPauta, StatusEscalaPauta statusEscalaPauta1, StatusAnaliseComparecimento statusAnaliseComparecimento, OrgaoJulgadorEntity orgaoJulgador, SalaEntity sala) {
        this.data = data;
        this.turno = turno;
        this.statusEscalaAvaliador = statusEscalaPauta;
        this.statusEscalaPautista = statusEscalaPauta1;
        this.analiseComparecimento = statusAnaliseComparecimento;
        this.orgaoJulgador = orgaoJulgador;
        this.sala = sala;
    }
}
