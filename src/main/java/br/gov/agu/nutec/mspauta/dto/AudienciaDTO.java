package br.gov.agu.nutec.mspauta.dto;



import br.gov.agu.nutec.mspauta.enums.Prioridade;
import br.gov.agu.nutec.mspauta.enums.Turno;
import br.gov.agu.nutec.mspauta.enums.Uf;

import java.time.LocalDate;
import java.util.List;


public record AudienciaDTO(
        String cnj,
        LocalDate data,
        String hora,
        Turno turno,
        String sala,
        String orgaoJulgador,
        String poloAtivo,
        String poloPassivo,
        String classeJudicial,
        List<String> advogados,
        String assunto,
        String tipo,
        String situacao,
        Prioridade prioridade,
        Uf uf
) {

}
