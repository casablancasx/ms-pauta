package br.gov.agu.nutec.mspauta.repository;

import br.gov.agu.nutec.mspauta.entity.AdvogadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface AdvogadoRepository extends JpaRepository<AdvogadoEntity, Long> {
    Optional<AdvogadoEntity> findByNome(String nome);
    List<AdvogadoEntity> findAllByNomeIn(Set<String> nomes);

}
