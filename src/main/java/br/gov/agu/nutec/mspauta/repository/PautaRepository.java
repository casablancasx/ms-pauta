package br.gov.agu.nutec.mspauta.repository;

import br.gov.agu.nutec.mspauta.entity.PautaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PautaRepository extends JpaRepository<PautaEntity, Long> {
}
