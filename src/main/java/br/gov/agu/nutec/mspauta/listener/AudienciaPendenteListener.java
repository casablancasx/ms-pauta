package br.gov.agu.nutec.mspauta.listener;

import br.gov.agu.nutec.mspauta.dto.AudienciaDTO;
import br.gov.agu.nutec.mspauta.dto.AudienciaMessage;
import br.gov.agu.nutec.mspauta.service.PautaService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static br.gov.agu.nutec.mspauta.enums.Status.FINALIZADO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Component
@RequiredArgsConstructor
public class AudienciaPendenteListener {


    private final PautaService pautaService;

    private final Queue<AudienciaMessage> buffer = new ConcurrentLinkedQueue<>();


    @RabbitListener(queues = "${rabbitmq.queue.sollux-ms-pauta}")
    public void audienciaPendente(AudienciaMessage audienciaMessage) {
        buffer.add(audienciaMessage);

        if (audienciaMessage.status() == FINALIZADO) {
            processarBuffer();
        }
    }


    private void processarBuffer() {
        HashSet<AudienciaDTO> audiencias = new HashSet<>();
        buffer.forEach(msg -> audiencias.add(msg.audiencia()));

        try {
            pautaService.criarPautas(audiencias);
        } catch (Exception e) {
            //TODO: implementar DLQ
            return;
        } finally {
            buffer.clear();
        }
    }

}
