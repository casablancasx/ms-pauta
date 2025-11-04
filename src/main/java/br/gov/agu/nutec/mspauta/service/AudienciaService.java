package br.gov.agu.nutec.mspauta.service;

import br.gov.agu.nutec.mspauta.dto.AudienciaDTO;
import br.gov.agu.nutec.mspauta.dto.request.AudienciaUpdateDTO;
import br.gov.agu.nutec.mspauta.dto.response.AudienciaResponseDTO;
import br.gov.agu.nutec.mspauta.entity.AudienciaEntity;
import br.gov.agu.nutec.mspauta.mapper.AudienciaMapper;
import br.gov.agu.nutec.mspauta.repository.AudienciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AudienciaService {

    private final AudienciaRepository audienciaRepository;
    private final AudienciaMapper audienciaMapper;


    public AudienciaResponseDTO atualizaAudiencia(AudienciaUpdateDTO audiencia) {

        AudienciaEntity audienciaOptional = audienciaRepository.findById(audiencia.audienciaId()).orElseThrow(
                () -> new RuntimeException("audiencia nao encontrada")
        );

        if (audiencia.analiseComparecimento() != null) {
            audienciaOptional.setAnaliseComparecimento(audiencia.analiseComparecimento());
        }

        if (audiencia.observacao() != null) {
            audienciaOptional.setAnalise(audiencia.observacao());
        }

        audienciaOptional = audienciaRepository.save(audienciaOptional);
        return audienciaMapper.toResponse(audienciaOptional);

    }

    public AudienciaEntity salvarAudiencia(AudienciaDTO audienciaDTO){


    }
}
