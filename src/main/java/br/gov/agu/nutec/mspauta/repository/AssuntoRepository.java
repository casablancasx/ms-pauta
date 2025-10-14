package br.gov.agu.nutec.mspauta.repository;

import br.gov.agu.nutec.mspauta.entity.AssuntoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssuntoRepository extends JpaRepository<AssuntoEntity, Integer> {

    Optional<AssuntoEntity> findAssuntoEntityByNomeIgnoreCase(String nome);


    List<AssuntoEntity> findByNomeContainsIgnoreCase(String nome);
}
