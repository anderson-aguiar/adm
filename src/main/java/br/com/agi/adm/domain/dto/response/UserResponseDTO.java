package br.com.agi.adm.domain.dto.response;

public record UserResponseDTO(
        Long id,
        String name,
        String email
) {}