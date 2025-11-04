package br.gov.agu.nutec.mspauta.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Entity
@Table(name = "tb_salas")
@Getter
@Service
@NoArgsConstructor
@AllArgsConstructor
public class SalaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sala_id")
    private Long salaId;

    private String nome;

    @ManyToOne
    @JoinColumn(name = "orgao_julgador_id")
    private OrgaoJulgadorEntity orgaoJulgador;

    @OneToMany(mappedBy = "sala")
    private List<AudienciaEntity> audiencias;



    public SalaEntity(String nome, OrgaoJulgadorEntity orgaoJulgador) {
        this.nome = nome;
        this.orgaoJulgador = orgaoJulgador;
    }
}
