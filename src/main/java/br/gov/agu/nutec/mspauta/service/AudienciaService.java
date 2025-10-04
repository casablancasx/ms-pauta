package br.gov.agu.nutec.mspauta.service;

import br.gov.agu.nutec.mspauta.dto.AudienciaDTO;
import br.gov.agu.nutec.mspauta.entity.AdvogadoEntity;
import br.gov.agu.nutec.mspauta.entity.AudienciaEntity;
import br.gov.agu.nutec.mspauta.entity.PautaEntity;
import br.gov.agu.nutec.mspauta.repository.AudienciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static br.gov.agu.nutec.mspauta.enums.StatusAnalise.ANALISE_PENDENTE;
import static br.gov.agu.nutec.mspauta.enums.StatusCadastro.CADASTRO_PENDETE;

@Service
@RequiredArgsConstructor
public class AudienciaService {

    private final AudienciaRepository audienciaRepository;
    private final AdvogadoService advogadoService;

    @Transactional
    public List<AudienciaEntity> criarAudiencias(List<AudienciaDTO> audiencias, PautaEntity pauta) {
        Set<String> nomesAdvogados = audiencias.stream()
                .flatMap(a -> a.advogados().stream())
                .collect(Collectors.toSet());

        Map<String, AdvogadoEntity> advogadosPorNome = advogadoService.ensureAdvogadosByNames(nomesAdvogados);

        List<AudienciaEntity> entidades = audiencias.stream()
                .map(a -> {
                    List<AdvogadoEntity> advogados = a.advogados().stream()
                            .map(advogadosPorNome::get)
                            .toList();

                    return new AudienciaEntity(
                            null,
                            a.cnj(),
                            a.classeJudicial(),
                            a.assunto(),
                            a.poloAtivo(),
                            a.hora(),
                            advogados,
                            a.prioridade(),
                            pauta,
                            CADASTRO_PENDETE,
                            CADASTRO_PENDETE,
                            ANALISE_PENDENTE
                    );
                })
                .toList();

        return audienciaRepository.saveAll(entidades);
    }
}
