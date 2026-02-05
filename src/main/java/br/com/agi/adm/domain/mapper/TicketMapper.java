package br.com.agi.adm.domain.mapper;

import br.com.agi.adm.domain.dto.request.CommentsRequestDTO;
import br.com.agi.adm.domain.dto.request.TicketRequestDTO;
import br.com.agi.adm.domain.dto.response.TicketResponseDTO;
import br.com.agi.adm.domain.entity.Assignee;
import br.com.agi.adm.domain.entity.Comment;
import br.com.agi.adm.domain.entity.Ticket;
import br.com.agi.adm.domain.entity.User;
import br.com.agi.adm.domain.enums.TicketPriority;
import br.com.agi.adm.domain.enums.TicketStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static br.com.agi.adm.domain.enums.TicketPriority.LOW;
import static br.com.agi.adm.domain.enums.TicketStatus.UNDEFINED;

@Component
public class TicketMapper {


    public Ticket toEntity(TicketRequestDTO dto) {
        if (dto == null) return null;

        Ticket ticket = new Ticket();
        ticket.setTitle(dto.title());
        ticket.setDescription(dto.description());
        if (dto.status() != null) {
            try {
                ticket.setStatus(TicketStatus.valueOf(dto.status().toUpperCase()));
            } catch (IllegalArgumentException e) {
                ticket.setStatus(UNDEFINED);
            }
        }
        if (dto.priority() != null) {
            try {
                ticket.setPriority(TicketPriority.valueOf(dto.priority().toUpperCase()));
            } catch (IllegalArgumentException e) {
                ticket.setPriority(LOW);
            }
        }
        ticket.setCreatedAt(dto.createdAt());

        if (dto.user() != null) {
            User user = new User();
            user.setName(dto.user().getName());
            user.setEmail(dto.user().getEmail());
            ticket.setUser(user);
        }

        if (dto.assignee() != null) {
            Assignee assignee = new Assignee();
            assignee.setName(dto.assignee().getName());
            ticket.setAssignee(assignee);
        }
        if (dto.comments() != null) {
            List<Comment> comments = new ArrayList<>();
            for (CommentsRequestDTO c : dto.comments()) {
                Comment comment = new Comment();
                comment.setAuthor(c.author());
                comment.setMessage(c.message());
                comment.setCreatedAt(c.createdAt());
                comment.setTicket(ticket);
                comments.add(comment);
            }
            ticket.setComments(comments);
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
