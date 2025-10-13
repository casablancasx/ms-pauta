package br.gov.agu.nutec.mspauta.repository;

import br.gov.agu.nutec.mspauta.entity.PautaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PautaRepository extends JpaRepository<PautaEntity, Long> {

    @Query("""
    SELECT p FROM PautaEntity p
    JOIN p.orgaoJulgador o
    JOIN o.uf uf
    JOIN p.sala s
    WHERE (:statusAnalise IS NULL OR p.analiseComparecimento = :statusAnalise)
      AND (:ufId IS NULL OR uf.ufId = :ufId)
      AND (:orgaoJulgadorId IS NULL OR o.orgaoJulgadorId = :orgaoJulgadorId)
      AND (:salaId IS NULL OR s.salaId = :salaId)
    """)
    Page<PautaEntity> listarPautas(@Param("statusAnalise") String statusAnalise,
                                   @Param("ufId") Integer ufId,
                                   @Param("orgaoJulgadorId") Long orgaoJulgadorId,
                                   @Param("salaId") Long salaId,
                                   Pageable pageable);

}
