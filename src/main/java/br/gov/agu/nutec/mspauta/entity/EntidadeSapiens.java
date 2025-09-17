package br.gov.agu.nutec.mspauta.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class EntidadeSapiens {

    private Integer sapiensId;

    private String nome;

    private String cpf;

    private String email;

    private Integer setorId;

    private Integer unidadeId;

    private LocalDateTime criadoEm;

}
