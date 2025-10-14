package br.gov.agu.nutec.mspauta.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_pautista")
public class PautistaEntity extends EntidadeSapiens {
}
