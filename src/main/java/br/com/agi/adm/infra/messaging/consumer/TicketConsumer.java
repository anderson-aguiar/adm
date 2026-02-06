package br.com.agi.adm.infra.messaging.consumer;

import br.com.agi.adm.domain.dto.request.TicketRequestDTO;
import br.com.agi.adm.application.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;

@RequiredArgsConstructor
public class TicketConsumer {

    private final TicketService ticketService;

    @Bean
    public Consumer<TicketRequestDTO> ticketConsumer() {

        return dto -> {

            ticketService.save(dto);
            System.out.println("Mensagem recebida: " + dto);

        };

    }

}
