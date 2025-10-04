package br.gov.agu.nutec.mspauta.repository;

import br.gov.agu.nutec.mspauta.entity.ClasseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClasseRepository extends JpaRepository<ClasseEntity, Integer> {
}
