package br.gov.agu.nutec.mspauta.service;

import br.gov.agu.nutec.mspauta.dto.AudienciaDTO;
import br.gov.agu.nutec.mspauta.dto.PageResponse;
import br.gov.agu.nutec.mspauta.dto.PautaDTO;
import br.gov.agu.nutec.mspauta.dto.response.PautaResponseDTO;
import br.gov.agu.nutec.mspauta.entity.*;
import br.gov.agu.nutec.mspauta.enums.Uf;
import br.gov.agu.nutec.mspauta.mapper.PautaMapper;
import br.gov.agu.nutec.mspauta.repository.*;
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

import static br.gov.agu.nutec.mspauta.enums.StatusAnaliseComparecimento.ANALISE_PENDENTE;
import static br.gov.agu.nutec.mspauta.enums.StatusCadastro.PENDENTE;
import static br.gov.agu.nutec.mspauta.enums.StatusEscalaPauta.ESCALA_PENDENTE;

@Slf4j
@Service
@RequiredArgsConstructor
public class PautaService {

    private final OrgaoJulgadorRepository orgaoJulgadorRepository;
    private final PautaRepository pautaRepository;
    private final AudienciaRepository audienciaRepository;
    private final UfRepostiory ufRepostiory;
    private final SalaRepository salaRepository;
    private final AdvogadoRepository advogadoRepository;
    private final PautaMapper pautaMapper;




    @Transactional
    public void criarPautas(Set<AudienciaDTO> audiencias) {

        Map<PautaDTO, List<AudienciaDTO>> agrupadas = agruparAudienciasPorPauta(audiencias);

        for (var entry : agrupadas.entrySet()) {
            PautaDTO chave = entry.getKey();

            List<AudienciaDTO> listaAudiencias = entry.getValue();

            UfEntity uf = buscarOuCriarUf(chave.uf());

            OrgaoJulgadorEntity orgaoJulgador = buscarOuCriarOrgaoJulgador(chave.orgaoJulgador(), uf);

            SalaEntity sala = buscarOuCriarSala(chave.sala(), orgaoJulgador);

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

            List<AudienciaEntity> entidadesAudiencia = criarAudiencias(listaAudiencias, pauta);

            audienciaRepository.saveAll(entidadesAudiencia);
        }
    }

    private UfEntity buscarOuCriarUf(Uf sigla) {
        return ufRepostiory.findBySigla(sigla)
                .orElseGet(() -> ufRepostiory.save(new UfEntity(null, sigla, new ArrayList<>())));
    }

    private OrgaoJulgadorEntity buscarOuCriarOrgaoJulgador(String nome, UfEntity uf) {
        return orgaoJulgadorRepository.findByNome(nome)
                .orElseGet(() -> orgaoJulgadorRepository.save(
                        new OrgaoJulgadorEntity(null, nome, uf,new ArrayList<>(), new ArrayList<>()))
                );
    }

    private SalaEntity buscarOuCriarSala(String nome, OrgaoJulgadorEntity orgaoJulgador) {
        return salaRepository.findByNome(nome)
                .orElseGet(() -> salaRepository.save(
                        new SalaEntity(null, nome, orgaoJulgador, new ArrayList<>()))
                );
    }

    private List<AudienciaEntity> criarAudiencias(List<AudienciaDTO> audiencias, PautaEntity pauta) {
        Set<String> nomesAdvogados = audiencias.stream()
                .flatMap(a -> a.advogados().stream())
                .collect(Collectors.toSet());

        Map<String, AdvogadoEntity> advogadosExistentes = advogadoRepository.findAllByNomeIn(nomesAdvogados)
                .stream()
                .collect(Collectors.toMap(AdvogadoEntity::getNome, a -> a));

        List<AdvogadoEntity> novosAdvogados = nomesAdvogados.stream()
                .filter(nome -> !advogadosExistentes.containsKey(nome))
                .map(nome -> new AdvogadoEntity(null, nome, false, new ArrayList<>()))
                .toList();

        if (!novosAdvogados.isEmpty()) {
            advogadoRepository.saveAll(novosAdvogados);
            novosAdvogados.forEach(a -> advogadosExistentes.put(a.getNome(), a));
        }

        return audiencias.stream()
                .map(a -> {
                    List<AdvogadoEntity> advogados = a.advogados().stream()
                            .map(advogadosExistentes::get)
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
                            PENDENTE,
                            PENDENTE
                    );
                })
                .toList();
    }

    private Map<PautaDTO, List<AudienciaDTO>> agruparAudienciasPorPauta(Set<AudienciaDTO> audiencias) {
        return audiencias.stream()
                .collect(Collectors.groupingBy(
                        a -> new PautaDTO(a.data(), a.orgaoJulgador(), a.sala(), a.turno(),a.uf())
                ));
    }

    public PageResponse<PautaResponseDTO> listarPautas(int page, int size, String statusAnalise, String uf, String orgaoJulgador, String sala) {
        Pageable pageable = PageRequest.of(page, size);
        var pautasPage = pautaRepository.listarPautas(statusAnalise, uf, orgaoJulgador, sala, pageable);

        List<PautaResponseDTO> dtos = pautasPage.getContent().stream()
            .map(pautaMapper::toResponseDTO)
            .toList();
        return new PageResponse<>(dtos, pautasPage.getTotalElements(), pautasPage.getTotalPages(), pautasPage.getNumber());
    }
}
