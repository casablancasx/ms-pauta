package br.gov.agu.nutec.mspauta.repository;

import br.gov.agu.nutec.mspauta.entity.OrgaoJulgadorEntity;
import br.gov.agu.nutec.mspauta.entity.UfEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrgaoJulgadorRepository extends JpaRepository<OrgaoJulgadorEntity, Long> {

    List<OrgaoJulgadorEntity> findOrgaoJulgadorByUf_UfId(Integer ufId);

    Optional<OrgaoJulgadorEntity> findByNomeAndUf(String nome, UfEntity uf);
}
