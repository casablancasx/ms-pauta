package br.gov.agu.nutec.mspauta.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.exchange.audiencia-pendente}")
    private String exchangeAudienciaPendente;

    @Value("${rabbitmq.queue.sollux-ms-pauta}")
    private String filaAudienciaPendenteMsPace;

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public DirectExchange directExchange(){
        return ExchangeBuilder.directExchange(exchangeAudienciaPendente).build();
    }

    @Bean
    public Queue filaAudienciaPendenteMsPauta(){
        return QueueBuilder.durable(filaAudienciaPendenteMsPace).build();
    }

    @Bean
    public Binding bindingFilaAudienciaPendenteMsPace(DirectExchange directExchange, Queue filaAudienciaPendenteMsPauta){
        return BindingBuilder.bind(filaAudienciaPendenteMsPauta).to(directExchange).with("audiencia.pendente");
    }
}
