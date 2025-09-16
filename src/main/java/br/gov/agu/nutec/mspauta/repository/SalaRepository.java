package br.gov.agu.nutec.mspauta.repository;

import br.gov.agu.nutec.mspauta.entity.SalaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SalaRepository extends JpaRepository<SalaEntity, Long> {

    Optional<SalaEntity> findByNome(String nome);
}
