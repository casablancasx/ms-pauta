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

    @Query("SELECT p FROM PautaEntity p " +
           "JOIN p.orgaoJulgador o " +
           "JOIN o.uf uf " +
           "JOIN p.sala s " +
           "WHERE (:statusAnalise IS NULL OR p.analiseComparecimento= :statusAnalise) " +
           "AND (:uf IS NULL OR uf.sigla = :uf) " +
           "AND (:orgaoJulgador IS NULL OR o.nome = :orgaoJulgador) " +
           "AND (:sala IS NULL OR s.nome = :sala)")
    Page<PautaEntity> listarPautas(@Param("statusAnalise") String statusAnalise,
                                   @Param("uf") String uf,
                                   @Param("orgaoJulgador") String orgaoJulgador,
                                   @Param("sala") String sala,
                                   Pageable pageable);

}
