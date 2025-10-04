package br.gov.agu.nutec.mspauta.service;

import br.gov.agu.nutec.mspauta.entity.AdvogadoEntity;
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

    /**
     * Garante que todos os nomes informados possuam um registro de AdvogadoEntity persistido.
     * Busca os existentes e cria os que faltarem. Retorna um mapa nome -> entidade.
     */
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
                .map(nome -> new AdvogadoEntity(null, nome, false, new ArrayList<>()))
                .toList();

        if (!novos.isEmpty()) {
            advogadoRepository.saveAll(novos);
            novos.forEach(a -> existentes.put(a.getNome(), a));
        }

        return existentes;
    }
}
