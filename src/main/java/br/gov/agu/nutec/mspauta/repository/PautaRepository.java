package br.gov.agu.nutec.mspauta.repository;

import br.gov.agu.nutec.mspauta.entity.PautaEntity;
import br.gov.agu.nutec.mspauta.enums.AnaliseComparecimento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PautaRepository extends JpaRepository<PautaEntity, Long> {

    @Query("""
    SELECT DISTINCT pauta FROM PautaEntity pauta
    LEFT JOIN pauta.audiencias audiencia
    LEFT JOIN pauta.escala escala
    LEFT JOIN audiencia.assunto assunto
    LEFT JOIN pauta.orgaoJulgador orgaoJulgador
    LEFT JOIN orgaoJulgador.uf uf
    LEFT JOIN pauta.sala sala
    WHERE (:ufId IS NULL OR uf.ufId = :ufId)
      AND (:orgaoJulgadorId IS NULL OR orgaoJulgador.orgaoJulgadorId = :orgaoJulgadorId)
      AND (:salaId IS NULL OR sala.salaId = :salaId)
      AND (:assuntoId IS NULL OR assunto.assuntoId = :assuntoId)
      AND (:prioritarias IS NULL OR audiencia.isPrioritario = :prioritarias)
      AND (:avaliadorId IS NULL OR escala.avaliador.sapiensId = :avaliadorId)
    """)
    Page<PautaEntity> listarPautas(
                                   @Param("ufId")  Integer ufId,
                                   @Param("orgaoJulgadorId") Long orgaoJulgadorId,
                                   @Param("salaId") Long salaId,
                                   @Param("assuntoId") Integer assuntoId,
                                   @Param("prioritarias") Boolean prioritarias,
                                   @Param("avaliadorId") Long avaliadorId,
                                   Pageable pageable);

}
