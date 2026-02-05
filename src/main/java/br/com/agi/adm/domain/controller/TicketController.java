package br.com.agi.adm.domain.controller;

import br.com.agi.adm.domain.dto.request.TicketRequestDTO;
import br.com.agi.adm.domain.dto.response.TicketResponseDTO;
import br.com.agi.adm.domain.service.TicketService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ticket")
public class TicketController {

    private final TicketService ticketService;

    @PostMapping
    public ResponseEntity<TicketResponseDTO> insert(@RequestBody @NonNull TicketRequestDTO dto){

        var response = ticketService.save(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
