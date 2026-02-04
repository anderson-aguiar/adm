package br.com.agi.adm.domain.dto.request;

import br.com.agi.adm.domain.entity.Assignee;
import br.com.agi.adm.domain.entity.User;

import java.time.LocalDateTime;
import java.util.List;

public record TicketRequestDTO(
        String title,
        String description,
        String status,
        String priority,
        User user,
        Assignee assignee,
        LocalDateTime createdAt,
        List<CommentsRequestDTO> comments
) {}