package br.gov.agu.nutec.mspauta.service;

import br.gov.agu.nutec.mspauta.dto.response.UfResponse;
import br.gov.agu.nutec.mspauta.entity.UfEntity;
import br.gov.agu.nutec.mspauta.enums.Uf;
import br.gov.agu.nutec.mspauta.mapper.UfMapper;
import br.gov.agu.nutec.mspauta.repository.UfRepostiory;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UfService {

    private final UfRepostiory repostiory;
    private final UfMapper mapper;

    @Transactional
    public UfEntity buscarUfPorSigla(Uf sigla) {
        return repostiory.findBySigla(sigla).orElseGet(() -> criarNovaUf(sigla));
    }


    private UfEntity criarNovaUf(Uf sigla){
        return repostiory.save(new UfEntity(sigla));
    }

    public List<UfResponse> listarUfs() {
        List<UfEntity> ufs = repostiory.findAll();
        return ufs.stream().map(mapper::mapToResponse).toList();
    }
}
