package br.gov.agu.nutec.mspauta.entity;

import br.gov.agu.nutec.mspauta.enums.AnaliseComparecimento;
import br.gov.agu.nutec.mspauta.enums.StatusCadastroTarefa;
import br.gov.agu.nutec.mspauta.enums.TipoContestacao;
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

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "classe_judicial_id")
    private ClasseJudicialEntity classeJudicial;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
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
    @Column(name = "status_cadastro_tarefa_avaliador")
    private StatusCadastroTarefa statusCadastroTarefaAvaliador;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_cadastro_tarefa_pautista")
    private StatusCadastroTarefa statusCadastroTarefaPautista;

    @Column(name = "analise_comparecimento")
    private AnaliseComparecimento analiseComparecimento;

    @Enumerated(EnumType.STRING)
    private TipoContestacao tipoContestacao;

    @Column(columnDefinition = "TEXT")
    private String analise;


    public AudienciaEntity(String cnj, ClasseJudicialEntity classeJudicial, AssuntoEntity assunto, String s, String hora, List<AdvogadoEntity> advogados, boolean prioridade, StatusCadastroTarefa statusCadastroTarefa, PautaEntity pauta, StatusCadastroTarefa statusCadastroTarefa1, AnaliseComparecimento analiseComparecimento, TipoContestacao tipoContestacao) {
        this.numeroProcesso = cnj;
        this.classeJudicial = classeJudicial;
        this.assunto = assunto;
        this.nomeParte = s;
        this.horario = hora;
        this.advogados = advogados;
        this.isPrioritario = prioridade;
        this.statusCadastroTarefaAvaliador = statusCadastroTarefa;
        this.pauta = pauta;
        this.statusCadastroTarefaPautista = statusCadastroTarefa1;
        this.analiseComparecimento = analiseComparecimento;
        this.tipoContestacao = tipoContestacao;
    }
}
