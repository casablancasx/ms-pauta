package br.gov.agu.nutec.mspauta.controller;

import br.gov.agu.nutec.mspauta.dto.response.PautaDetalhadaDTO;
import br.gov.agu.nutec.mspauta.dto.response.PautaResponseDTO;
import br.gov.agu.nutec.mspauta.dto.PageResponse;
import br.gov.agu.nutec.mspauta.enums.AnaliseComparecimento;
import br.gov.agu.nutec.mspauta.service.PautaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pauta")
@RequiredArgsConstructor
public class PautaController {


    private final PautaService pautaService;



    @GetMapping
    public ResponseEntity<PageResponse<PautaResponseDTO>> listarPautas(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(required = false) Integer ufId,
            @RequestParam(required = false) Long orgaoJulgadorId,
            @RequestParam(required = false) Long salaId,
            @RequestParam(required = false) Integer assuntoId,
            @RequestParam(required = false) Boolean prioritarias,
            @RequestParam(required = false) Long avaliadorId,
            @RequestParam(required = false) Long pautistaId,
            @RequestHeader("Authorization") String token
    ){
        String tokenHeader = token.replace("Bearer ", "");
        var response = pautaService.listarPautas(page, size,  ufId, orgaoJulgadorId, salaId, assuntoId, prioritarias, avaliadorId, pautistaId,tokenHeader);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PautaDetalhadaDTO> bucarPautaPorId(@PathVariable Long id){
        var response = pautaService.buscarPautaPorId(id);
        return ResponseEntity.ok(response);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPauta(@PathVariable long id){
        pautaService.deletarPauta(id);
        return ResponseEntity.noContent().build();
    }
}
