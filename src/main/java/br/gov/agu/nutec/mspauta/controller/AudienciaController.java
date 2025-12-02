package br.gov.agu.nutec.mspauta.controller;

import br.gov.agu.nutec.mspauta.dto.PageResponse;
import br.gov.agu.nutec.mspauta.dto.request.AudienciaUpdateDTO;
import br.gov.agu.nutec.mspauta.dto.response.AudienciaResponseDTO;
import br.gov.agu.nutec.mspauta.service.AudienciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/audiencia")
@RequiredArgsConstructor
public class AudienciaController {


    private final AudienciaService service;


    @PatchMapping
    public ResponseEntity<AudienciaResponseDTO> atualizarAudiencia(@RequestBody  AudienciaUpdateDTO audiencia){
        AudienciaResponseDTO response = service.atualizaAudiencia(audiencia);
        return ResponseEntity.ok(response);
    }


    @GetMapping
    public ResponseEntity<PageResponse<AudienciaResponseDTO>>listarAudiencias(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(required = false) Long orgaoJulgadorId,
            @RequestParam(required = false) String numeroProcesso
    ){
        var response = service.buscarAudiencias(page,size,orgaoJulgadorId,numeroProcesso);
        return ResponseEntity.ok(response);
    }
}
