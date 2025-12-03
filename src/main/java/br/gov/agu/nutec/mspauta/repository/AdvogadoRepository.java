package br.gov.agu.nutec.mspauta.repository;

import br.gov.agu.nutec.mspauta.entity.AdvogadoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface AdvogadoRepository extends JpaRepository<AdvogadoEntity, Long> {
    Optional<AdvogadoEntity> findByNome(String nome);
    List<AdvogadoEntity> findAllByNomeIn(Set<String> nomes);

    @Query(value = "SELECT a FROM AdvogadoEntity a WHERE a.isPrioritario = :isPrioritario AND (:nome IS NULL OR a.nome LIKE %:nome%)")
    Page<AdvogadoEntity> findByIsPrioritario(boolean isPrioritario,String nome, Pageable pageable);
}
