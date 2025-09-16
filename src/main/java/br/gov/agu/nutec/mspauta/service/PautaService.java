package br.gov.agu.nutec.mspauta.service;

import br.gov.agu.nutec.mspauta.dto.AudienciaDTO;
import br.gov.agu.nutec.mspauta.dto.PautaDTO;
import br.gov.agu.nutec.mspauta.entity.*;
import br.gov.agu.nutec.mspauta.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PautaService {
    private final OrgaoJulgadorRepository orgaoJulgadorRepository;
    private final PautaRepository pautaRepository;
    private final AudienciaRepository audienciaRepository;
    private final SalaRepository salaRepository;
    private final AdvogadoRepository advogadoRepository;

    public void criarPautas(HashSet<AudienciaDTO> audiencias) {
        Map<PautaDTO, List<AudienciaDTO>> audienciasAgrupadas = agruparAudienciasPorPauta(audiencias);
        salvarDados(audienciasAgrupadas);
    }

    private void salvarDados(Map<PautaDTO, List<AudienciaDTO>> audienciasAgrupdas) {
        for (Map.Entry<PautaDTO, List<AudienciaDTO>> entry : audienciasAgrupdas.entrySet()) {

            var orgaoJulgador = orgaoJulgadorRepository.findByNome(entry.getKey().orgaoJulgador()).orElseGet(() -> orgaoJulgadorRepository.save(new OrgaoJulgadorEntity(null, entry.getKey().orgaoJulgador(), List.of(), List.of())));

            var sala = salaRepository.findByNome(entry.getKey().sala()).orElseGet(() -> salaRepository.save(new SalaEntity(null, entry.getKey().sala(), orgaoJulgador, List.of())));

            var pauta = pautaRepository.save(new PautaEntity(null, entry.getKey().data(), entry.getKey().turno().getDescricao(), orgaoJulgador, sala, List.of()));

            var audiencias = entry.getValue().stream().map(a -> {
                List<AdvogadoEntity> advogados = a.advogados().stream().map(advogadoNome -> advogadoRepository.findByNome(advogadoNome).orElseGet(() -> advogadoRepository.save(new AdvogadoEntity(null, advogadoNome, List.of())))).collect(Collectors.toList());
                return new AudienciaEntity(null, a.cnj(), a.classeJudicial(), a.assunto(), a.poloAtivo(), advogados, a.prioridade().name(), pauta);
            }).toList();

            audienciaRepository.saveAll(audiencias);
        }
    }

    private Map<PautaDTO, List<AudienciaDTO>> agruparAudienciasPorPauta(HashSet<AudienciaDTO> audiencias) {
        return audiencias.stream().collect(Collectors.groupingBy(a -> new PautaDTO(a.data(), a.orgaoJulgador(), a.sala(), a.turno())));
    }
}