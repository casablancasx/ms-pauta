package br.gov.agu.nutec.mspauta.service;

import br.gov.agu.nutec.mspauta.dto.response.OrgaoJulgadorResponse;
import br.gov.agu.nutec.mspauta.entity.OrgaoJulgadorEntity;
import br.gov.agu.nutec.mspauta.entity.UfEntity;
import br.gov.agu.nutec.mspauta.enums.Uf;
import br.gov.agu.nutec.mspauta.mapper.OrgaoJulgadorMapper;
import br.gov.agu.nutec.mspauta.repository.OrgaoJulgadorRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrgaoJulgadorService {

    private final OrgaoJulgadorRepository repository;
    private final OrgaoJulgadorMapper mapper;

    @Transactional
    public OrgaoJulgadorEntity buscarPorNomeEUf(String nome, UfEntity uf){
        return repository.findByNomeAndUf(nome, uf).orElseGet(() -> criarNovoOrgaoJulgador(nome, uf));

    }


    private OrgaoJulgadorEntity criarNovoOrgaoJulgador(String nome, UfEntity uf){
        return repository.save(new OrgaoJulgadorEntity(nome,uf));
    }

    public List<OrgaoJulgadorResponse> listarOrgaoJulgadores(String nome, List<Uf> ufs){
        List<OrgaoJulgadorEntity> orgaos = repository.buscarOrgaoJulgadorPorNomeEUf(nome,ufs);
        return orgaos.stream().map(mapper::mapToResponse).toList();
    }


}
