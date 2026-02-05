package br.com.agi.adm.domain.dto.request;

import br.com.agi.adm.domain.entity.Assignee;
import br.com.agi.adm.domain.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

public record TicketRequestDTO(
        String title,
        String description,
        String status,
        String priority,
        User user,
        @JsonProperty("assignedTo")
        Assignee assignee,
        LocalDateTime createdAt,
        List<CommentsRequestDTO> comments
) {}