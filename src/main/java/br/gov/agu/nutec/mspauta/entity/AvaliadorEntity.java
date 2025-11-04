package br.gov.agu.nutec.mspauta.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_avaliadores")
public class AvaliadorEntity extends EntidadeSapiens {

    @OneToMany
    private List<AudienciaEntity> audiencias;
}
