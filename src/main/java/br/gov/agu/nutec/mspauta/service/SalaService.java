package br.gov.agu.nutec.mspauta.service;

import br.gov.agu.nutec.mspauta.entity.OrgaoJulgadorEntity;
import br.gov.agu.nutec.mspauta.entity.SalaEntity;
import br.gov.agu.nutec.mspauta.repository.SalaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SalaService {

    private final SalaRepository repository;

    @Transactional
    public SalaEntity buscarSalaPorNomeEOrgaoJulgador(String nome, OrgaoJulgadorEntity orgaoJulgador){
        return repository.findByNomeAndOrgaoJulgador(nome, orgaoJulgador).orElseGet(() -> criarNovaSala(nome, orgaoJulgador));
    }



    private SalaEntity criarNovaSala(String nome, OrgaoJulgadorEntity orgaoJulgador){
        return repository.save(new SalaEntity(nome, orgaoJulgador));
    }
}
