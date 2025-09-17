package br.gov.agu.nutec.mspauta.entity;

import br.gov.agu.nutec.mspauta.enums.TipoAfastamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "tb_afastamento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AfastamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "afastamento_id")
    private Long afastamentoId;

    private LocalDate inicio;

    private LocalDate fim;

    @Enumerated(EnumType.STRING)
    private TipoAfastamento tipoAfastamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "avaliador_id")
    private AvaliadorEntity avaliador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pautista_id")
    private PautistaEntity pautista;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adicionado_por_id")
    private UsuarioEntity adicionadoPor;

    private String observacao;
}
