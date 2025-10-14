package br.gov.agu.nutec.mspauta.service;

import br.gov.agu.nutec.mspauta.dto.AudienciaDTO;
import br.gov.agu.nutec.mspauta.dto.PageResponse;
import br.gov.agu.nutec.mspauta.dto.PautaDTO;
import br.gov.agu.nutec.mspauta.dto.request.PautaUpdateDTO;
import br.gov.agu.nutec.mspauta.dto.response.PautaResponseDTO;
import br.gov.agu.nutec.mspauta.entity.OrgaoJulgadorEntity;
import br.gov.agu.nutec.mspauta.entity.PautaEntity;
import br.gov.agu.nutec.mspauta.entity.SalaEntity;
import br.gov.agu.nutec.mspauta.entity.UfEntity;
import br.gov.agu.nutec.mspauta.enums.StatusAnaliseComparecimento;
import br.gov.agu.nutec.mspauta.enums.Uf;
import br.gov.agu.nutec.mspauta.mapper.PautaMapper;
import br.gov.agu.nutec.mspauta.repository.PautaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static br.gov.agu.nutec.mspauta.enums.StatusAnaliseComparecimento.ANALISE_PENDENTE;
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
                            chave.data(),
                            chave.turno(),
                            ESCALA_PENDENTE,
                            ESCALA_PENDENTE,
                            ANALISE_PENDENTE,
                            orgaoJulgador,
                            sala)
            );

            audienciaService.criarAudiencias(listaAudiencias, pauta);
        }
    }


    public PageResponse<PautaResponseDTO> listarPautas(int page, int size, StatusAnaliseComparecimento statusAnalise, Integer ufId, Long orgaoJulgadorId, Long salaId, Integer assuntoId, Boolean prioritarias, Long avaliadorId) {
        Pageable pageable = PageRequest.of(page, size);

        var pautasPage = pautaRepository.listarPautas(statusAnalise, ufId, orgaoJulgadorId, salaId,assuntoId,prioritarias, avaliadorId, pageable);

        List<PautaResponseDTO> dtos = pautasPage.getContent().stream()
                .map(pautaMapper::toResponseDTO)
                .toList();

        return new PageResponse<>(dtos, page, size, pautasPage.getTotalElements(), pautasPage.getNumber());
    }

    public PautaResponseDTO atualizarComparecimento(PautaUpdateDTO request) {
        PautaEntity pauta = pautaRepository.findById(request.pautaId()).orElseThrow(
                () -> new RuntimeException("Pauta id não encontrada")
        );

        pauta.setAnaliseComparecimento(request.analiseComparecimento());
        pauta = pautaRepository.save(pauta);
        return pautaMapper.toResponseDTO(pauta);
    }

    public void deletarPauta(long id) {
        pautaRepository.deleteById(id);
    }

    public PautaResponseDTO buscarPautaPorId(Long id) {
        PautaEntity pauta = pautaRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Pauta id não encontrada")
        );
        return pautaMapper.toResponseDTO(pauta);
    }
}
