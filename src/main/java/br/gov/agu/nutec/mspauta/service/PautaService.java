package br.gov.agu.nutec.mspauta.service;

import br.gov.agu.nutec.mspauta.dto.AudienciaDTO;
import br.gov.agu.nutec.mspauta.dto.PageResponse;
import br.gov.agu.nutec.mspauta.dto.PautaDTO;
import br.gov.agu.nutec.mspauta.dto.response.PautaResponseDTO;
import br.gov.agu.nutec.mspauta.entity.OrgaoJulgadorEntity;
import br.gov.agu.nutec.mspauta.entity.PautaEntity;
import br.gov.agu.nutec.mspauta.entity.SalaEntity;
import br.gov.agu.nutec.mspauta.entity.UfEntity;
import br.gov.agu.nutec.mspauta.mapper.PautaMapper;
import br.gov.agu.nutec.mspauta.repository.OrgaoJulgadorRepository;
import br.gov.agu.nutec.mspauta.repository.PautaRepository;
import br.gov.agu.nutec.mspauta.repository.SalaRepository;
import br.gov.agu.nutec.mspauta.repository.UfRepostiory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static br.gov.agu.nutec.mspauta.enums.StatusAnalise.ANALISE_PENDENTE;
import static br.gov.agu.nutec.mspauta.enums.StatusEscalaPauta.ESCALA_PENDENTE;

@Slf4j
@Service
@RequiredArgsConstructor
public class PautaService {

    private final PautaRepository pautaRepository;

    private final AudienciaService audienciaService;
    private final SalaService salaService;
    private final OrgaoJulgadorService orgaoJulgadorService;
    private final UfService ufService;

    private final PautaMapper pautaMapper;


    private Map<PautaDTO, List<AudienciaDTO>> agruparAudienciasPorPauta(Set<AudienciaDTO> audiencias) {
        return audiencias.stream()
                .collect(Collectors.groupingBy(
                        a -> new PautaDTO(a.data(), a.orgaoJulgador(), a.sala(), a.turno(), a.uf())
                ));
    }


    @Transactional
    public void criarPautas(Set<AudienciaDTO> audiencias) {

        Map<PautaDTO, List<AudienciaDTO>> agrupadas = agruparAudienciasPorPauta(audiencias);

        for (var entry : agrupadas.entrySet()) {
            PautaDTO chave = entry.getKey();

            List<AudienciaDTO> listaAudiencias = entry.getValue();

            UfEntity uf = ufService.buscarUfPorSigla(chave.uf());

            OrgaoJulgadorEntity orgaoJulgador = orgaoJulgadorService.buscarPorNomeEUf(chave.orgaoJulgador(), uf);

            SalaEntity sala = salaService.buscarSalaPorNomeEOrgaoJulgador(chave.sala(), orgaoJulgador);

            PautaEntity pauta = pautaRepository.save(
                    new PautaEntity(
                            null,
                            chave.data(),
                            chave.turno(),
                            ESCALA_PENDENTE,
                            ESCALA_PENDENTE,
                            ANALISE_PENDENTE,
                            orgaoJulgador,
                            sala,
                            new ArrayList<>()
                    )
            );

            audienciaService.criarAudiencias(listaAudiencias, pauta);
        }
    }


    public PageResponse<PautaResponseDTO> listarPautas(int page, int size, String statusAnalise, String uf, String orgaoJulgador, String sala) {
        Pageable pageable = PageRequest.of(page, size);
        var pautasPage = pautaRepository.listarPautas(statusAnalise, uf, orgaoJulgador, sala, pageable);

        List<PautaResponseDTO> dtos = pautasPage.getContent().stream()
                .map(pautaMapper::toResponseDTO)
                .toList();

        return new PageResponse<>(dtos, page, size, pautasPage.getTotalElements(), pautasPage.getNumber());
    }
}
