package br.gov.agu.nutec.mspauta.entity;

import br.gov.agu.nutec.mspauta.enums.Prioridade;
import br.gov.agu.nutec.mspauta.enums.StatusAnalise;
import br.gov.agu.nutec.mspauta.enums.StatusCadastro;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tb_audiencias")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AudienciaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long audienciaId;

    private String numeroProcesso;

    private String classeJudicial;

    private String assunto;

    private String nomeParte;

    private String horario;

    @ManyToMany
    @JoinTable(
            name = "tb_audiencia_advogado",
            joinColumns = @JoinColumn(name = "audiencia_id"),
            inverseJoinColumns = @JoinColumn(name = "advogado_id")
    )
    private List<AdvogadoEntity> advogados;

    @Enumerated(EnumType.STRING)
    private Prioridade prioridade;

    @ManyToOne
    @JoinColumn(name = "pauta_id")
    private PautaEntity pauta;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_cadastro_avaliador")
    private StatusCadastro statusCadastroAvaliador;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_cadastro_pautista")
    private StatusCadastro statusCadastroPautista;


    private StatusAnalise resultadoAnalise;

}
