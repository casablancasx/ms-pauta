package br.gov.agu.nutec.mspauta.repository;

import br.gov.agu.nutec.mspauta.entity.UfEntity;
import br.gov.agu.nutec.mspauta.enums.Uf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UfRepostiory extends JpaRepository<UfEntity, Integer> {

    Optional<UfEntity> findBySigla(Uf sigla);
}
