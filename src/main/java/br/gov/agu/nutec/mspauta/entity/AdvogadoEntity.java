package br.gov.agu.nutec.mspauta.entity;

import br.gov.agu.nutec.mspauta.enums.Uf;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_advogados")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdvogadoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long advogadoId;

    private String nome;

    @Column(name = "is_prioritario")
    private boolean isPrioritario;

    @ManyToMany
    @JoinTable(
            name = "tb_advogado_ufs",
            joinColumns = @JoinColumn(name = "advogado_id"),
            inverseJoinColumns = @JoinColumn(name = "uf_id")
    )
    private List<UfEntity> ufs = new ArrayList<>();

    @ManyToMany(mappedBy = "advogados")
    private List<AudienciaEntity> audiencias;


    public AdvogadoEntity(String nome, List<UfEntity> ufs) {
        this.nome = nome;
        this.isPrioritario = true;
        this.ufs = ufs;
        this.audiencias = new ArrayList<>();
    }
}
