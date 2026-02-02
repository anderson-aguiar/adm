package br.com.agi.adm.dto;

import java.time.Instant;

public record ComentarioDTO(
        String author,
        String message,
        Instant createdAt
) {}
