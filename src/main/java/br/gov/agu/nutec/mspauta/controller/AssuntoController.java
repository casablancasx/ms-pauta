package br.gov.agu.nutec.mspauta.controller;

import br.gov.agu.nutec.mspauta.dto.response.AssuntoResponse;
import br.gov.agu.nutec.mspauta.service.AssuntoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/assunto")
@RequiredArgsConstructor
public class AssuntoController {


    private final AssuntoService service;


    @GetMapping
    public ResponseEntity<List<AssuntoResponse>> listarAssuntos(@RequestParam String nome){
        List<AssuntoResponse> response = service.buscarAssuntosPorNome(nome);
        return ResponseEntity.ok(response);
    }
}
