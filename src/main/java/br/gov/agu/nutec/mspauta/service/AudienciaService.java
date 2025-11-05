package br.gov.agu.nutec.mspauta.service;

import br.gov.agu.nutec.mspauta.dto.AudienciaDTO;
import br.gov.agu.nutec.mspauta.dto.request.AudienciaUpdateDTO;
import br.gov.agu.nutec.mspauta.dto.response.AudienciaResponseDTO;
import br.gov.agu.nutec.mspauta.entity.*;
import br.gov.agu.nutec.mspauta.enums.StatusAnaliseComparecimento;
import br.gov.agu.nutec.mspauta.enums.TipoContestacao;
import br.gov.agu.nutec.mspauta.mapper.AudienciaMapper;
import br.gov.agu.nutec.mspauta.repository.AdvogadoRepository;
import br.gov.agu.nutec.mspauta.repository.AudienciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AudienciaService {

    private final AudienciaRepository audienciaRepository;
    private final AdvogadoRepository advogadoRepository;
    private final AudienciaMapper audienciaMapper;
    private final OrgaoJulgadorService orgaoJulgadorService;
    private final UfService ufService;
    private final SalaService salaService;
    private final AssuntoService assuntoService;
    private final AdvogadoService advogadoService;


    public AudienciaResponseDTO atualizaAudiencia(AudienciaUpdateDTO audiencia) {

        AudienciaEntity audienciaOptional = audienciaRepository.findById(audiencia.audienciaId()).orElseThrow(
                () -> new RuntimeException("audiencia nao encontrada")
        );

        if (audiencia.analiseComparecimento() != null) {
            audienciaOptional.setAnaliseComparecimento(audiencia.analiseComparecimento());
        }

        if (audiencia.observacao() != null) {
            audienciaOptional.setAnalise(audiencia.observacao());
        }

        audienciaOptional = audienciaRepository.save(audienciaOptional);
        return audienciaMapper.toResponse(audienciaOptional);

    }

    public void salvarAudiencia(AudienciaDTO audienciaDTO){

        UfEntity uf = ufService.buscarUfPorSigla(audienciaDTO.getUf());
        OrgaoJulgadorEntity orgaoJulgadorEntity = orgaoJulgadorService.buscarPorNomeEUf(audienciaDTO.getOrgaoJulgador(), uf);
        SalaEntity salaEntity = salaService.buscarSalaPorNomeEOrgaoJulgador(audienciaDTO.getSala(), orgaoJulgadorEntity);
        AssuntoEntity assuntoEntity = assuntoService.buscarAssunto(audienciaDTO.getAssunto());
        var advogadoEntity = advogadoService.ensureAdvogadosByNames(audienciaDTO.getAdvogados());
        AudienciaEntity novaAudiencia = new AudienciaEntity();

        novaAudiencia.setNumeroProcesso(audienciaDTO.getCnj());
        novaAudiencia.setHorarioDaAudiencia(audienciaDTO.getHora());
        novaAudiencia.setAssunto(assuntoEntity);
        novaAudiencia.setNomeParte(audienciaDTO.getPoloAtivo());
        novaAudiencia.setOrgaoJulgador(orgaoJulgadorEntity);
        novaAudiencia.setAdvogados(advogadoEntity.values().stream().toList());
        novaAudiencia.setSala(salaEntity);
        novaAudiencia.setPrioritario(verificarAudienciaPrioritaria(audienciaDTO.getAdvogados()));
        novaAudiencia.setStatusCadastroAvaliador(false);
        novaAudiencia.setStatusCadastroPautista(false);
        novaAudiencia.setTipoContestacao(audienciaDTO.getTipoContestacao());

        if (audienciaDTO.getTipoContestacao().equals(TipoContestacao.TIPO2) || audienciaDTO.getTipoContestacao().equals(TipoContestacao.BRANCO)){
            novaAudiencia.setAnaliseComparecimento(StatusAnaliseComparecimento.ANALISE_PENDENTE);
        }else {
            novaAudiencia.setAnaliseComparecimento(StatusAnaliseComparecimento.NAO_COMPARECIMENTO);
        }
    }



    private boolean verificarAudienciaPrioritaria(List<String> advogados) {
        List<AdvogadoEntity> advogadosList = advogadoRepository.findAllByNomeIn(advogados);
        return advogadosList.stream().anyMatch(AdvogadoEntity::isPrioritario);
    }
}
