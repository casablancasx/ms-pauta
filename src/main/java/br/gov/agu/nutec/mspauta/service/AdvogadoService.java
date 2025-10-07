package br.gov.agu.nutec.mspauta.service;

import br.gov.agu.nutec.mspauta.dto.PageResponse;
import br.gov.agu.nutec.mspauta.dto.response.AdvogadoResponseDTO;
import br.gov.agu.nutec.mspauta.entity.AdvogadoEntity;
import br.gov.agu.nutec.mspauta.entity.UfEntity;
import br.gov.agu.nutec.mspauta.enums.Uf;
import br.gov.agu.nutec.mspauta.repository.AdvogadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdvogadoService {

    private final AdvogadoRepository advogadoRepository;
    private final UfService ufService;


    @Transactional
    public Map<String, AdvogadoEntity> ensureAdvogadosByNames(Set<String> nomesAdvogados) {
        if (nomesAdvogados == null || nomesAdvogados.isEmpty()) {
            return Collections.emptyMap();
        }

        Map<String, AdvogadoEntity> existentes = advogadoRepository.findAllByNomeIn(nomesAdvogados)
                .stream()
                .collect(Collectors.toMap(AdvogadoEntity::getNome, a -> a));

        List<AdvogadoEntity> novos = nomesAdvogados.stream()
                .filter(nome -> !existentes.containsKey(nome))
                .map(nome -> {
                    AdvogadoEntity a = new AdvogadoEntity();
                    a.setNome(nome);
                    a.setPrioritario(false);
                    return a;
                })
                .toList();

        if (!novos.isEmpty()) {
            advogadoRepository.saveAll(novos);
            novos.forEach(a -> existentes.put(a.getNome(), a));
        }

        return existentes;
    }


    @Transactional
    public AdvogadoResponseDTO cadastrarAdvogadoPrioritario(String nome, List<Uf> ufs) {


        List<UfEntity> entidadesUf = ufs.stream()
                .filter(Objects::nonNull)
                .distinct()
                .map(ufService::buscarUfPorSigla)
                .toList();

        AdvogadoEntity advogado = advogadoRepository.save(new AdvogadoEntity(nome, entidadesUf));

        return new AdvogadoResponseDTO(advogado.getAdvogadoId(), advogado.getNome(), ufs, advogado.isPrioritario());
    }

    public PageResponse<AdvogadoResponseDTO> listarAdvogados(int page, int size, boolean prioritarios) {


        var pageable = org.springframework.data.domain.PageRequest.of(page, size);

        var advogadosPage = prioritarios
                ? advogadoRepository.findByIsPrioritario(true, pageable)
                : advogadoRepository.findAll(pageable);

        var dtos = advogadosPage.getContent().stream()
                .map(a -> new AdvogadoResponseDTO(
                        a.getAdvogadoId(),
                        a.getNome(),
                        Optional.ofNullable(a.getUfs()).orElseGet(Collections::emptyList)
                                .stream()
                                .map(UfEntity::getSigla)
                                .toList(),
                        a.isPrioritario()
                ))
                .toList();

        return new PageResponse<>(
                dtos,
                advogadosPage.getNumber(),
                advogadosPage.getSize(),
                advogadosPage.getTotalElements(),
                advogadosPage.getTotalPages()
        );
    }

    public void deletarAdvogado(long id) {
        advogadoRepository.deleteById(id);
    }


}
