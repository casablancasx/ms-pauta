package br.gov.agu.nutec.mspauta.controller;

import br.gov.agu.nutec.mspauta.dto.response.SalaResponse;
import br.gov.agu.nutec.mspauta.service.SalaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sala")
@RequiredArgsConstructor
public class SalaController {

    private final SalaService service;

    @GetMapping
    public ResponseEntity<List<SalaResponse>> listarSalasPorOrgaoJulgador(@RequestParam Long orgaoJulgadorId){
        List<SalaResponse> response = service.listarSalasPorOrgaoJulgador(orgaoJulgadorId);
        return ResponseEntity.ok(response);
    }
}

