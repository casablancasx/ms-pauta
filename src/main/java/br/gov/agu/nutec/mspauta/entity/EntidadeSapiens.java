package br.gov.agu.nutec.mspauta.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class EntidadeSapiens {

    @Id
    @Column(name = "sapiens_id")
    private Long sapiensId;

    private String nome;

    private String cpf;

    private String email;

    private Integer setorId;

    private Integer unidadeId;

    private LocalDateTime criadoEm;

}
