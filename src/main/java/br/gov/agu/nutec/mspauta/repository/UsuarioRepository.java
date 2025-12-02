package br.gov.agu.nutec.mspauta.repository;


import br.gov.agu.nutec.mspauta.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {


}
