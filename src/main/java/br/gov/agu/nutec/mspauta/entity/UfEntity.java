package br.gov.agu.nutec.mspauta.entity;

import br.gov.agu.nutec.mspauta.enums.Uf;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tb_ufs")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UfEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ufId;

    @Enumerated(EnumType.STRING)
    private Uf sigla;

    @OneToMany(mappedBy = "uf")
    private List<OrgaoJulgadorEntity> orgaosJulgadores;


    public UfEntity(Uf sigla) {
        this.sigla = sigla;
    }
}
