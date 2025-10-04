package br.gov.agu.nutec.mspauta.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Entity
@Table(name = "tb_orgaos_julgadores")
@Getter
@Service
@NoArgsConstructor
@AllArgsConstructor
public class OrgaoJulgadorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orgao_julgador_id")
    private Long orgaoJulgadorId;

    private String nome;

    @ManyToOne
    @JoinColumn(name = "uf_id")
    private UfEntity uf;

    @OneToMany(mappedBy = "orgaoJulgador")
    private List<PautaEntity> pautas;

    @OneToMany(mappedBy = "orgaoJulgador")
    private List<SalaEntity> salas;


    public OrgaoJulgadorEntity(String nome, UfEntity uf) {
        this.nome = nome;
        this.uf = uf;
    }
}
