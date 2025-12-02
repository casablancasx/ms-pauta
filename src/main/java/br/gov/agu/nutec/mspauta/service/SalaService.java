package br.gov.agu.nutec.mspauta.service;

import br.gov.agu.nutec.mspauta.dto.response.SalaResponse;
import br.gov.agu.nutec.mspauta.entity.OrgaoJulgadorEntity;
import br.gov.agu.nutec.mspauta.entity.SalaEntity;
import br.gov.agu.nutec.mspauta.mapper.SalaMapper;
import br.gov.agu.nutec.mspauta.repository.SalaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SalaService {

    private final SalaRepository repository;
    private final SalaMapper mapper;

    @Transactional
    public SalaEntity buscarSalaPorNomeEOrgaoJulgador(String nome, OrgaoJulgadorEntity orgaoJulgador){
        return repository.findByNomeAndOrgaoJulgador(nome, orgaoJulgador).orElseGet(() -> criarNovaSala(nome, orgaoJulgador));
    }



    private SalaEntity criarNovaSala(String nome, OrgaoJulgadorEntity orgaoJulgador){
        return repository.save(new SalaEntity(nome, orgaoJulgador));
    }

    public List<SalaResponse> listarSalasPorOrgaoJulgador(Long orgaoJulgadorId) {
        List<SalaEntity> salas = repository.findAllByOrgaoJulgador_OrgaoJulgadorId(orgaoJulgadorId);
        return salas.stream().map(mapper::mapToResponse).toList();
    }
}
