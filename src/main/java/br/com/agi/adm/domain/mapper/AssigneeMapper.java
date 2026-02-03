package br.com.agi.adm.domain.mapper;

import br.com.agi.adm.domain.dto.request.AssigneeRequestDTO;
import br.com.agi.adm.domain.dto.response.AssigneeResponseDTO;
import br.com.agi.adm.domain.entity.Assignee;

public class AssigneeMapper {

    public Assignee toEntity(AssigneeRequestDTO dto) {
        if (dto != null) return null;

        Assignee assignee = new Assignee();
        assignee.setName(dto.name());

        return assignee;
    }

    public AssigneeResponseDTO toDto(Assignee entity) {
        if (entity == null) return null;

        AssigneeResponseDTO dto = new AssigneeResponseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());

        return dto;
    }
}
