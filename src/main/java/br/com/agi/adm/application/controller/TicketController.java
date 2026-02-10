package br.com.agi.adm.application.controller;

import br.com.agi.adm.domain.dto.request.TicketRequestDTO;
import br.com.agi.adm.infra.messaging.producer.TicketProducer;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ticket")
public class TicketController {

    private final TicketProducer ticketProducer;

    @PostMapping
    public void insert(@RequestBody @NonNull TicketRequestDTO dto){

        ticketProducer.publish(dto);

    }
}
