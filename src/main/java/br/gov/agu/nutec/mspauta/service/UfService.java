package br.gov.agu.nutec.mspauta.service;

import br.gov.agu.nutec.mspauta.entity.UfEntity;
import br.gov.agu.nutec.mspauta.enums.Uf;
import br.gov.agu.nutec.mspauta.repository.UfRepostiory;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UfService {

    private final UfRepostiory repostiory;

    @Transactional
    public UfEntity buscarUfPorSigla(Uf sigla) {
        return repostiory.findBySigla(sigla).orElseGet(() -> criarNovaUf(sigla));
    }


    private UfEntity criarNovaUf(Uf sigla){
        return repostiory.save(new UfEntity(sigla));
    }
}
