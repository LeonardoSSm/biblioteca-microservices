package com.leonardo.notifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ReservaListener {
    private static final Logger log = LoggerFactory.getLogger(ReservaListener.class);

    @RabbitListener(queues = "${app.amqp.queue:reservas.disponibilizadas}")
    public void receber(Long reservaId){
        log.info("Notificação: reserva disponibilizada, id={}", reservaId);
    }
}
