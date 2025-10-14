package br.gov.agu.nutec.mspauta.service;

import br.gov.agu.nutec.mspauta.dto.response.AssuntoResponse;
import br.gov.agu.nutec.mspauta.entity.AssuntoEntity;
import br.gov.agu.nutec.mspauta.mapper.AssuntoMapper;
import br.gov.agu.nutec.mspauta.repository.AssuntoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AssuntoService {

    private final AssuntoRepository repository;
    private final AssuntoMapper mapper;


    public AssuntoEntity buscarAssunto(String assunto){
        return repository.findAssuntoEntityByNomeIgnoreCase(assunto).orElseGet(() -> criarNovoAssunto(assunto));
    }

    private AssuntoEntity criarNovoAssunto(String assunto){
        return repository.save(new AssuntoEntity(assunto));
    }

    public List<AssuntoResponse> buscarAssuntosPorNome(String nome) {
        var assuntos = repository.findByNomeContainsIgnoreCase(nome);
        return  assuntos.stream().map(mapper::mapToResponse).toList();
    }
}
