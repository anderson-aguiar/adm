package br.com.agi.adm.infra.messaging.consumer;

import br.com.agi.adm.application.service.TicketService;
import br.com.agi.adm.domain.dto.request.TicketRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@RequiredArgsConstructor
@Configuration
public class TicketConsumer {

    private final TicketService ticketService;

    @Bean(name = "consumer")
    public Consumer<TicketRequestDTO> ticketConsumer() {

        return dto -> {

            ticketService.save(dto);
            System.out.println("Mensagem recebida: " + dto);

        };

    }

}
