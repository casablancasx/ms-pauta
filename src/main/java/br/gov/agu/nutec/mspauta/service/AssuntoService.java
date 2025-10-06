package br.gov.agu.nutec.mspauta.service;

import br.gov.agu.nutec.mspauta.entity.AssuntoEntity;
import br.gov.agu.nutec.mspauta.repository.AssuntoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AssuntoService {

    private final AssuntoRepository repository;


    public AssuntoEntity buscarAssunto(String assunto){
        return repository.findAssuntoEntityByNomeIgnoreCase(assunto).orElseGet(() -> criarNovoAssunto(assunto));
    }

    private AssuntoEntity criarNovoAssunto(String assunto){
        return repository.save(new AssuntoEntity(assunto));
    }

}
