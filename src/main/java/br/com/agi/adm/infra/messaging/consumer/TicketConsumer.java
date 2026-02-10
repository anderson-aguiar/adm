package br.com.agi.adm.infra.messaging.consumer;

import br.com.agi.adm.application.service.TicketService;
import br.com.agi.adm.domain.dto.request.TicketRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.ImmediateAcknowledgeAmqpException;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.util.Optional;
import java.util.function.Consumer;

@RequiredArgsConstructor
@Configuration
public class TicketConsumer {

    private final TicketService ticketService;
    private final StreamBridge streamBridge;

    @Bean(name = "consumer")
    public Consumer<Message<TicketRequestDTO>> ticketConsumer() {

        return dto -> {

            var retryCountNullable = dto.getHeaders().get("amqp_retryCount", Long.class);

            var retryCount = Optional.ofNullable(retryCountNullable).orElse(5L);

            var event = dto.getPayload();

            if ("ADM".equalsIgnoreCase(
                    Optional.ofNullable(event.assignedTo())
                            .map(a -> a.getName())
                            .orElse(""))) {
                ticketService.save(event);
                System.out.println("Mensagem processada com sucesso (ADM)");
                return;
            }

            if (retryCount < 5){
                throw new RuntimeException("Não foi possivel processar a mensagem!");
            }

            if (retryCount >= 5){
                streamBridge.send("final-dlq-ticket-out-0", dto.getPayload());
                throw new ImmediateAcknowledgeAmqpException("Enviado para a DLQ final!");
            }

            System.out.println("Mensagem recebida: " + dto);

        };
    }
}
