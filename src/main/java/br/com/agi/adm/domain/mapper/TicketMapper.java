package br.com.agi.adm.domain.mapper;


import br.com.agi.adm.domain.Assignee;
import br.com.agi.adm.domain.Ticket;
import br.com.agi.adm.domain.User;
import br.com.agi.adm.domain.dto.request.TicketRequestDTO;
import br.com.agi.adm.domain.dto.response.TicketResponseDTO;
import br.com.agi.adm.domain.enums.TicketPriority;
import br.com.agi.adm.domain.enums.TicketStatus;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {

    public Ticket toEntity(TicketRequestDTO dto) {
        if (dto == null) return null;

        Ticket ticket = new Ticket();
        ticket.setTitle(dto.title());
        ticket.setDescription(dto.description());
        ticket.setStatus(dto.status() != null
                ? TicketStatus.valueOf(dto.status().toUpperCase())
                : null);
        ticket.setPriority(dto.priority() != null
                ? TicketPriority.valueOf(dto.priority().toUpperCase())
                : null);
        ticket.setCreatedAt(dto.createdAt());

        if (dto.userId() != null) {
            User user = new User();
            user.setId(dto.userId());
            ticket.setUser(user);
        }

        if (dto.assigneeId() != null) {
            Assignee assignee = new Assignee();
            assignee.setId(dto.assigneeId());
            ticket.setAssignee(assignee);
        }

        return ticket;
    }

    public TicketResponseDTO toDto(Ticket entity) {
        if (entity == null) return null;

        TicketResponseDTO dto = new TicketResponseDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setStatus(entity.getStatus());
        dto.setPriority(entity.getPriority());
        dto.setUserId(entity.getUser() != null ? entity.getUser().getId() : null);
        dto.setAssigneeId(entity.getAssignee() != null ? entity.getAssignee().getId() : null);
        dto.setCreatedAt(entity.getCreatedAt());

        return dto;
    }


}
