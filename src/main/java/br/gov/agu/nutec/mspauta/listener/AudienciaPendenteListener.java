package br.gov.agu.nutec.mspauta.listener;

import br.gov.agu.nutec.mspauta.dto.AudienciaMessage;
import br.gov.agu.nutec.mspauta.service.AudienciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AudienciaPendenteListener {


    private final AudienciaService audienciaService;



    @RabbitListener(queues = "${rabbitmq.queue.sollux-ms-pauta}", concurrency = "2")
    public void audienciaPendente(AudienciaMessage audienciaMessage) {

        audienciaService.salvarAudiencia(audienciaMessage.audiencia());

    }

}
