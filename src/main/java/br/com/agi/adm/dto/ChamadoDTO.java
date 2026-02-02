package br.com.agi.adm.dto;

import java.time.Instant;
import java.util.List;

public record ChamadoDTO(
    String id,
    String title,
    String description,
    String status,
    String priority,
    Instant createdAt,
    UsuarioDTO user,
    ResponsavelDTO assignedTo,
    List<ComentarioDTO> comments
) {}
