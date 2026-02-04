package br.com.agi.adm.domain.dto.request;

import java.time.LocalDateTime;

public record CommentsRequestDTO(
        String author,
        String message,
        LocalDateTime createdAt
) {}