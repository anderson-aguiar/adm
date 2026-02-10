package br.com.agi.adm.infra.messaging.consumer;

import br.com.agi.adm.application.service.TicketService;
import br.com.agi.adm.domain.dto.request.TicketRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.ImmediateAcknowledgeAmqpException;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.text.Normalizer;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

@RequiredArgsConstructor
@Configuration
public class TicketConsumer {

    private final TicketService ticketService;
    private final StreamBridge streamBridge;

    @Bean(name = "consumer")
    public Consumer<Message<TicketRequestDTO>> ticketConsumer() {

        return dto -> {

            var retryCount = Optional.ofNullable(
                    dto.getHeaders().get("amqp_retryCount", Long.class)
            ).orElse(0L);

            var event = dto.getPayload();

            String assigneeName = Optional.ofNullable(event.assignedTo())
                    .map(a -> a.getName())
                    .orElse("");

            String normalized = Normalizer.normalize(assigneeName, Normalizer.Form.NFD)
                    .replaceAll("\\p{M}", "")
                    .toUpperCase();

            Set<String> validNames = Set.of("ADM", "ADMINISTRACAO");

            if (validNames.contains(normalized)) {
                ticketService.save(event);
                System.out.println("Mensagem aceita e salva (" + assigneeName + ")");
                return;
            }

            if (retryCount < 5) {
                throw new RuntimeException("Não foi possível processar a mensagem!");
            }

            streamBridge.send("final-dlq-ticket-out-0", event);
            throw new ImmediateAcknowledgeAmqpException("Enviado para a DLQ final!");
        };
    }
}