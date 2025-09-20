package br.gov.agu.nutec.mspauta.entity;


import br.gov.agu.nutec.mspauta.enums.StatusAnaliseComparecimento;
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

    private String turno;

    @Column(name = "status_analise_comparecimento")
    @Enumerated(EnumType.STRING)
    private StatusAnaliseComparecimento statusAnaliseComparecimento;

    @ManyToOne
    @JoinColumn(name = "orgao_julgador_id")
    private OrgaoJulgadorEntity orgaoJulgador;


    @ManyToOne
    @JoinColumn(name = "sala_id")
    private SalaEntity sala;


    @OneToMany(mappedBy = "pauta")
    private List<AudienciaEntity> audiencias;


}
