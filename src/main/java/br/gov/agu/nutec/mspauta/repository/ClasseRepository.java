package br.gov.agu.nutec.mspauta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClasseRepository extends JpaRepository<ClasseJudicialEntity, Integer> {

    Optional<ClasseJudicialEntity> findClasseEntityByNomeIgnoreCase(String nome);
}
