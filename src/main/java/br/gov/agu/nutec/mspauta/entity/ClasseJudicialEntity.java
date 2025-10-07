package br.gov.agu.nutec.mspauta.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "tb_classes_judiciais")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClasseJudicialEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "classe_judicial_id")
    private Integer classeJudicialId;

    private String nome;

    @OneToMany(mappedBy = "classeJudicial")
    private Set<AudienciaEntity> audiencias;

    public ClasseJudicialEntity(String nome) {
        this.nome = nome;
    }


}
