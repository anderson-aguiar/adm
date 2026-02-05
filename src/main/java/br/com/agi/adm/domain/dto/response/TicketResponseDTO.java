package br.com.agi.adm.domain.dto.response;

import br.com.agi.adm.domain.enums.TicketPriority;
import br.com.agi.adm.domain.enums.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class TicketResponseDTO {

    private Long id;
    private String title;
    private String description;
    private TicketStatus status;
    private TicketPriority priority;
    private Long userId;
    private Long assigneeId;
    private LocalDateTime createdAt;


}
