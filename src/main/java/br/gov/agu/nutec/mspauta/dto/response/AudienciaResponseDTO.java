package br.gov.agu.nutec.mspauta.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AudienciaResponseDTO {

    private Long audienciaId;

    private String numeroProcesso;

    private String hora;

    private String nomeParte;

    private List<String> advogados;

    private String assunto;

    private String classeJudicial;

    private boolean isPrioritaria;

    private String statusComparecimento;

    private String analise;

}
