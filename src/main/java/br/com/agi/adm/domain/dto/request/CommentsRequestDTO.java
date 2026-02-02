package br.com.agi.adm.domain.dto.request;

import java.time.LocalDateTime;

public record CommentsRequestDTO(
        String message,
        Long userId,
        Long assigneeId,
        LocalDateTime createdAt
) {}