package br.gov.agu.nutec.mspauta.service;

import br.gov.agu.nutec.mspauta.entity.ClasseJudicialEntity;
import br.gov.agu.nutec.mspauta.repository.ClasseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClasseJudicialService {

    private final ClasseRepository repository;



    public ClasseJudicialEntity buscarClassePorNome(String nome){

        return repository.findClasseEntityByNomeIgnoreCase(nome).orElseGet(() -> criarNovaClasse(nome));
    }


    private ClasseJudicialEntity criarNovaClasse(String nome){
        return repository.save(new ClasseJudicialEntity( nome));
    }

}
