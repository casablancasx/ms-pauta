package br.gov.agu.nutec.mspauta.entity;

import br.gov.agu.nutec.mspauta.enums.StatusAnaliseComparecimento;
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

    @Column(
            name = "numero_processo",
            nullable = false,
            unique = true,
            length = 25
    )
    private String numeroProcesso;


    @Column(
            name = "horario_da_audiencia",
            nullable = false,
            length = 5
    )
    private String horarioDaAudiencia;


    @ManyToOne
    @JoinColumn(name = "assunto_id")
    private AssuntoEntity assunto;

    private String nomeParte;


    @ManyToMany
    @JoinTable(
            name = "tb_audiencia_advogado",
            joinColumns = @JoinColumn(name = "audiencia_id"),
            inverseJoinColumns = @JoinColumn(name = "advogado_id")
    )
    private List<AdvogadoEntity> advogados;

    @Column(name = "audiencia_prioritaria", nullable = false)
    private boolean isPrioritario;

    @Column(name = "cadastrado_sapiens_para_avaliador", nullable = false)
    private boolean statusCadastroAvaliador;

    @Column(name = "cadastrado_sapiens_para_pautista", nullable = false)
    private boolean statusCadastroPautista;

    private StatusAnaliseComparecimento analiseComparecimento;

    @Column(columnDefinition = "TEXT")
    private String analise;

}
