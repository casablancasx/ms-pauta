package br.gov.agu.nutec.mspauta.repository;

import br.gov.agu.nutec.mspauta.entity.AudienciaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AudienciaRepository extends JpaRepository<AudienciaEntity, Long> {
}
