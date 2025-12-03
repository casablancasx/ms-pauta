package br.gov.agu.nutec.mspauta.controller;

import br.gov.agu.nutec.mspauta.dto.PageResponse;
import br.gov.agu.nutec.mspauta.dto.request.AdvogadoRequestDTO;
import br.gov.agu.nutec.mspauta.dto.response.AdvogadoResponseDTO;
import br.gov.agu.nutec.mspauta.dto.response.PautaResponseDTO;
import br.gov.agu.nutec.mspauta.service.AdvogadoService;
import br.gov.agu.nutec.mspauta.service.PautaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/advogado")
@RequiredArgsConstructor
public class AdvogadoController {


    private final AdvogadoService service;



    @GetMapping
    public ResponseEntity<PageResponse<AdvogadoResponseDTO>> listaAdvogadosPrioritarios(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(required = false) String nome
    ){

        var response = service.listarAdvogados(page,size, nome);
        return ResponseEntity.ok(response);
    }


    @PostMapping
    public ResponseEntity<AdvogadoResponseDTO> cadastrarAdvogadoPrioritario(@RequestBody AdvogadoRequestDTO request){
        var response = service.cadastrarAdvogadoPrioritario(request.nome(),request.ufs());
        return ResponseEntity.created(URI.create("/advogado/" + response.advogadoId())).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAdvogado(@PathVariable long id){
        service.deletarAdvogado(id);
        return ResponseEntity.noContent().build();
    }
}
