package br.gov.agu.nutec.mspauta.service;

import br.gov.agu.nutec.mspauta.entity.OrgaoJulgadorEntity;
import br.gov.agu.nutec.mspauta.entity.UfEntity;
import br.gov.agu.nutec.mspauta.repository.OrgaoJulgadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrgaoJulgadorService {

    private final OrgaoJulgadorRepository repository;

    @Transactional
    public OrgaoJulgadorEntity buscarPorNomeEUf(String nome, UfEntity uf){
        return repository.findByNomeAndUf(nome, uf).orElseGet(() -> criarNovoOrgaoJulgador(nome, uf));

    }


    private OrgaoJulgadorEntity criarNovoOrgaoJulgador(String nome, UfEntity uf){
        return repository.save(new OrgaoJulgadorEntity(nome,uf));
    }




}
