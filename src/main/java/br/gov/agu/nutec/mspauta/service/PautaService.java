package br.gov.agu.nutec.mspauta.service;

import br.gov.agu.nutec.mspauta.dto.AudienciaDTO;
import br.gov.agu.nutec.mspauta.dto.PautaDTO;
import br.gov.agu.nutec.mspauta.entity.*;
import br.gov.agu.nutec.mspauta.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PautaService {

    private final OrgaoJulgadorRepository orgaoJulgadorRepository;
    private final PautaRepository pautaRepository;
    private final AudienciaRepository audienciaRepository;
    private final SalaRepository salaRepository;
    private final AdvogadoRepository advogadoRepository;

    @Transactional
    public void criarPautas(Set<AudienciaDTO> audiencias) {

        Map<PautaDTO, List<AudienciaDTO>> agrupadas = agruparAudienciasPorPauta(audiencias);

        for (var entry : agrupadas.entrySet()) {
            PautaDTO chave = entry.getKey();

            List<AudienciaDTO> listaAudiencias = entry.getValue();

            OrgaoJulgadorEntity orgaoJulgador = buscarOuCriarOrgaoJulgador(chave.orgaoJulgador());

            SalaEntity sala = buscarOuCriarSala(chave.sala(), orgaoJulgador);

            PautaEntity pauta = pautaRepository.save(
                    new PautaEntity(
                            null,
                            chave.data(),
                            chave.turno().name(),
                            orgaoJulgador,
                            sala,
                            new ArrayList<>()
                    )
            );

            List<AudienciaEntity> entidadesAudiencia = criarAudiencias(listaAudiencias, pauta);

            audienciaRepository.saveAll(entidadesAudiencia);
        }
    }

    private OrgaoJulgadorEntity buscarOuCriarOrgaoJulgador(String nome) {
        return orgaoJulgadorRepository.findByNome(nome)
                .orElseGet(() -> orgaoJulgadorRepository.save(
                        new OrgaoJulgadorEntity(null, nome, new ArrayList<>(), new ArrayList<>()))
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
                .map(nome -> new AdvogadoEntity(null, nome, new ArrayList<>()))
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
                            advogados,
                            a.prioridade().name(),
                            pauta,
                            false
                    );
                })
                .toList();
    }

    private Map<PautaDTO, List<AudienciaDTO>> agruparAudienciasPorPauta(Set<AudienciaDTO> audiencias) {
        return audiencias.stream()
                .collect(Collectors.groupingBy(
                        a -> new PautaDTO(a.data(), a.orgaoJulgador(), a.sala(), a.turno())
                ));
    }
}
