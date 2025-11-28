package br.gov.agu.nutec.mspauta.repository;

import br.gov.agu.nutec.mspauta.entity.OrgaoJulgadorEntity;
import br.gov.agu.nutec.mspauta.entity.UfEntity;
import br.gov.agu.nutec.mspauta.enums.Uf;
import br.gov.agu.nutec.mspauta.service.UfService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrgaoJulgadorRepository extends JpaRepository<OrgaoJulgadorEntity, Long> {

    @Query("SELECT o FROM OrgaoJulgadorEntity o WHERE o.uf.ufId IN :ufIds")
    List<OrgaoJulgadorEntity> findOrgaoJulgadorPorUfIds(List<Integer> ufIds);

    Optional<OrgaoJulgadorEntity> findByNomeAndUf(String nome, UfEntity uf);

    @Query("SELECT o FROM OrgaoJulgadorEntity o WHERE LOWER(o.nome) LIKE LOWER(CONCAT('%', :nome, '%')) AND (:ufs IS NULL OR o.uf.sigla IN :ufs)")
    List<OrgaoJulgadorEntity> buscarOrgaoJulgadorPorNomeEUf(String nome, List<Uf> ufs);
}
