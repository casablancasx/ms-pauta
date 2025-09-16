package br.gov.agu.nutec.mspauta.entity;

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

    @ManyToMany
    @JoinTable(
            name = "tb_audiencia_advogado",
            joinColumns = @JoinColumn(name = "audiencia_id"),
            inverseJoinColumns = @JoinColumn(name = "advogado_id")
    )
    private List<AdvogadoEntity> advogados;

    private String prioridade   ;

    @ManyToOne
    @JoinColumn(name = "pauta_id")
    private PautaEntity pauta;
}
