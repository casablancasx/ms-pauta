package br.gov.agu.nutec.mspauta.controller;

import br.gov.agu.nutec.mspauta.dto.response.UfResponse;
import br.gov.agu.nutec.mspauta.service.UfService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/uf")
@RequiredArgsConstructor
public class UfController {


    private final UfService service;


    @GetMapping
    public ResponseEntity<List<UfResponse>> listarUfs(){
        List<UfResponse> response = service.listarUfs();
        return ResponseEntity.ok(response);
    }
}
