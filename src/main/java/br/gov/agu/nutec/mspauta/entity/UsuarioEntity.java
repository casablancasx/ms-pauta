package br.gov.agu.nutec.mspauta.entity;


import br.gov.agu.nutec.mspauta.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tb_usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioEntity extends EntidadeSapiens {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer usuarioId;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "adicionadoPor")
    private List<UsuarioEntity> usuariosAdicionados;

    @OneToMany(mappedBy = "adicionadoPor")
    private List<PautistaEntity> pautistasAdicionados;

    @OneToMany(mappedBy = "adicionadoPor")
    private List<AvaliadorEntity> avaliadoresAdicionados;
}
