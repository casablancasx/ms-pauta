package br.gov.agu.nutec.mspauta.controller;

import br.gov.agu.nutec.mspauta.dto.request.PautaUpdateDTO;
import br.gov.agu.nutec.mspauta.dto.response.PautaResponseDTO;
import br.gov.agu.nutec.mspauta.dto.PageResponse;
import br.gov.agu.nutec.mspauta.enums.Uf;
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
            @RequestParam(required = false) String resultadoAnalise,
            @RequestParam(required = false) Integer ufId,
            @RequestParam(required = false) Long orgaoJulgadorId,
            @RequestParam(required = false) Long salaId
    ){

        var response = pautaService.listarPautas(page, size, resultadoAnalise, ufId, orgaoJulgadorId, salaId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PautaResponseDTO> bucarPautaPorId(@PathVariable Long id){
        PautaResponseDTO response = pautaService.buscarPautaPorId(id);
        return ResponseEntity.ok(response);

    }

    @PatchMapping("/analise-comparecimento")
    public ResponseEntity<PautaResponseDTO> atualizarAnaliseComparecimento(@RequestBody PautaUpdateDTO request){
        PautaResponseDTO resposne = pautaService.atualizarComparecimento(request);
        return ResponseEntity.ok(resposne);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPauta(@PathVariable long id){
        pautaService.deletarPauta(id);
        return ResponseEntity.noContent().build();
    }
}
