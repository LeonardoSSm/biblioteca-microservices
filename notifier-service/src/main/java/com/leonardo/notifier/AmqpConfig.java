package com.leonardo.notifier;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfig {
    @Bean
    public Exchange reservasExchange(@Value("${app.amqp.exchange:reservas.exchange}") String name){
        return ExchangeBuilder.topicExchange(name).durable(true).build();
    }
    @Bean
    public Queue reservasQueue(@Value("${app.amqp.queue:reservas.disponibilizadas}") String name){
        return QueueBuilder.durable(name).build();
    }
    @Bean
    public Binding binding(Queue q, Exchange ex,
                           @Value("${app.amqp.routingKey:reserva.disponibilizada}") String rk){
        return BindingBuilder.bind(q).to(ex).with(rk).noargs();
    }
}
