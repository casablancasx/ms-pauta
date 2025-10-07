package br.gov.agu.nutec.mspauta.entity;

import br.gov.agu.nutec.mspauta.enums.StatusAnaliseComparecimento;
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

    @ManyToOne
    @JoinColumn(name = "classe_judicial_id")
    private ClasseJudicialEntity classeJudicial;

    @ManyToOne
    @JoinColumn(name = "assunto_id")
    private AssuntoEntity assunto;

    private String nomeParte;

    private String horario;

    @ManyToMany
    @JoinTable(
            name = "tb_audiencia_advogado",
            joinColumns = @JoinColumn(name = "audiencia_id"),
            inverseJoinColumns = @JoinColumn(name = "advogado_id")
    )
    private List<AdvogadoEntity> advogados;

    private boolean isPrioritario;

    @ManyToOne
    @JoinColumn(name = "pauta_id")
    private PautaEntity pauta;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_cadastro_avaliador")
    private StatusCadastro statusCadastroAvaliador;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_cadastro_pautista")
    private StatusCadastro statusCadastroPautista;

    private StatusAnaliseComparecimento analiseComparecimento;

    @Column(columnDefinition = "TEXT")
    private String analise;

    public AudienciaEntity(String numeroProcesso, ClasseJudicialEntity classeJudicial, AssuntoEntity assunto, String nomeParte, String horario, List<AdvogadoEntity> advogados, boolean prioridade, StatusCadastro statusCadastroAvaliador, PautaEntity pauta, StatusCadastro statusCadastroPautista, StatusAnaliseComparecimento analiseComparecimento) {
        this.numeroProcesso = numeroProcesso;
        this.classeJudicial = classeJudicial;
        this.assunto = assunto;
        this.nomeParte = nomeParte;
        this.horario = horario;
        this.advogados = advogados;
        this.isPrioritario = prioridade;
        this.statusCadastroAvaliador = statusCadastroAvaliador;
        this.pauta = pauta;
        this.statusCadastroPautista = statusCadastroPautista;
        this.analiseComparecimento = analiseComparecimento;
    }
}
