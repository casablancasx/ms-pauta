package br.gov.agu.nutec.mspauta.entity;


import br.gov.agu.nutec.mspauta.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioEntity extends EntidadeSapiens {

    @Enumerated(EnumType.STRING)
    private Role role;
}
