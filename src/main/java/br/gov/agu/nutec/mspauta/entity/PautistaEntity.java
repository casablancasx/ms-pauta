package br.gov.agu.nutec.mspauta.entity;

import jakarta.persistence.Entity;
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
@Table(name = "tb_pautista")
public class PautistaEntity extends EntidadeSapiens {

    @OneToMany(mappedBy = "pautista")
    private List<AudienciaEntity> audiencias;
}
