package br.gov.agu.nutec.mspauta.repository;

import br.gov.agu.nutec.mspauta.entity.AudienciaEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AudienciaRepository extends JpaRepository<AudienciaEntity, Long> {

    @Query("SELECT a FROM AudienciaEntity a WHERE a.pauta.orgaoJulgador.orgaoJulgadorId = :orgaoJulgadorId")
    List<AudienciaEntity> findAudienciasPorOrgaoJulgadorId(Long orgaoJulgadorId);

    @Query("SELECT a FROM AudienciaEntity a WHERE " +
            "(:orgaoJulgadorId IS NULL OR a.pauta.orgaoJulgador.orgaoJulgadorId = :orgaoJulgadorId) AND " +
            "(:numeroProcesso IS NULL OR a.numeroProcesso LIKE %:numeroProcesso%)")
    List<AudienciaEntity> buscarAudienciaPorFiltro(Long orgaoJulgadorId, String numeroProcesso, Pageable pageable);
}
