package br.gov.agu.nutec.mspauta.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "tb_assuntos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssuntoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assunto_id")
    private Integer assuntoId;

    private String nome;

    @OneToMany(mappedBy = "assunto")
    private Set<AudienciaEntity> audiencias;

    public AssuntoEntity(String nome) {
        this.nome = nome;
    }

}
