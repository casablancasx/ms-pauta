package br.gov.agu.nutec.mspauta.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_pautista")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PautistaEntity extends EntidadeSapiens {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pautista_id")
    private Integer pautistaId;

    private Integer quantidadePautas;

    private Integer quantiadeAudiencias;

    private boolean disponivel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adicionado_por_id")
    private UsuarioEntity adicionadoPor;

    @OneToMany(mappedBy = "pautista", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AfastamentoEntity> afastamentos = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "tb_pautista_preferencias_orgaos",
        joinColumns = @JoinColumn(name = "pautista_id"),
        inverseJoinColumns = @JoinColumn(name = "orgao_julgador_id")
    )
    private List<OrgaoJulgadorEntity> preferenciasOrgaosJulgadores = new ArrayList<>();

    public double calcularCargaTrabalho() {
        final int PESO_PAUTA = 1;
        final int PESO_AUDIENCIA = 2;
        return (quantidadePautas * PESO_PAUTA) + (quantiadeAudiencias * PESO_AUDIENCIA);
    }

    public void adicionarPreferenciaOrgaoJulgador(OrgaoJulgadorEntity orgaoJulgador) {
        if (!preferenciasOrgaosJulgadores.contains(orgaoJulgador)) {
            preferenciasOrgaosJulgadores.add(orgaoJulgador);
        }
    }

    public void removerPreferenciaOrgaoJulgador(OrgaoJulgadorEntity orgaoJulgador) {
        preferenciasOrgaosJulgadores.remove(orgaoJulgador);
    }

    public boolean temPreferenciaPorOrgaoJulgador(OrgaoJulgadorEntity orgaoJulgador) {
        return preferenciasOrgaosJulgadores.contains(orgaoJulgador);
    }
}
