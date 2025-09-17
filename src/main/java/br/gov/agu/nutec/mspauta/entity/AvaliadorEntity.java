package br.gov.agu.nutec.mspauta.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_avaliadores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AvaliadorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer avaliadorId;

    private boolean disponivel;

    private Integer quantidadePautas;

    private Integer quantiadeAudiencias;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adicionado_por_id")
    private UsuarioEntity adicionadoPor;

    @OneToMany(mappedBy = "avaliador", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AfastamentoEntity> afastamentos = new ArrayList<>();



    public double calcularCargaTrabalho() {
        final int PESO_PAUTA = 1;
        final int PESO_AUDIENCIA = 2;
        return (quantidadePautas * PESO_PAUTA) + (quantiadeAudiencias * PESO_AUDIENCIA);
    }
}
