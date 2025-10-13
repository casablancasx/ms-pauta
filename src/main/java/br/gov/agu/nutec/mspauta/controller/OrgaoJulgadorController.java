package br.gov.agu.nutec.mspauta.controller;

import br.gov.agu.nutec.mspauta.dto.response.OrgaoJulgadorResponse;
import br.gov.agu.nutec.mspauta.enums.Uf;
import br.gov.agu.nutec.mspauta.service.OrgaoJulgadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orgao-julgador")
@RequiredArgsConstructor
public class OrgaoJulgadorController {

    private final OrgaoJulgadorService service;


    @GetMapping
    public ResponseEntity<List<OrgaoJulgadorResponse>> listarOrgaoJulgadores(
            @RequestParam Uf uf,
            @RequestParam String nome
    ) {
        List<OrgaoJulgadorResponse> response = service.listarOrgaoJulgadores(nome,uf);
        return ResponseEntity.ok(response);
    }


}
