package br.gov.agu.nutec.mspauta.dto;

import br.gov.agu.nutec.mspauta.enums.TipoContestacao;
import br.gov.agu.nutec.mspauta.enums.Turno;
import br.gov.agu.nutec.mspauta.enums.Uf;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AudienciaDTO {

    private String cnj;
    private LocalDate data;
    private String hora;
    private Turno turno;
    private String sala;
    private String orgaoJulgador;
    private String poloAtivo;
    private String poloPassivo;
    private String classeJudicial;
    private List<String> advogados;
    private String assunto;
    private String tipo;
    private String situacao;
    private Uf uf;
    private TipoContestacao tipoContestacao;

    public AudienciaDTO(String cnj, LocalDate data, String hora, Turno turno, String sala, String orgaoJulgador, String poloAtivo, String poloPassivo, String classeJudicial, List<String> advogados, String assunto, String tipo, String situacao, Uf uf) {
        this.cnj = cnj;
        this.data = data;
        this.hora = hora;
        this.turno = turno;
        this.sala = sala;
        this.orgaoJulgador = orgaoJulgador;
        this.poloAtivo = poloAtivo;
        this.poloPassivo = poloPassivo;
        this.classeJudicial = classeJudicial;
        this.advogados = advogados;
    }
}


