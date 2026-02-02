package br.com.agi.adm.domain.dto.request;

import java.time.LocalDateTime;
import java.util.List;

public record TicketRequestDTO(
        String title,
        String description,
        String status,
        String priority,
        Long userId,
        Long assigneeId,
        LocalDateTime createdAt,
        List<CommentsRequestDTO> comments
) {}