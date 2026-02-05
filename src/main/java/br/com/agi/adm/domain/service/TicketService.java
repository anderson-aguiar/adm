package br.com.agi.adm.domain.service;

import br.com.agi.adm.domain.dto.request.TicketRequestDTO;
import br.com.agi.adm.domain.dto.response.TicketResponseDTO;
import br.com.agi.adm.domain.entity.Assignee;
import br.com.agi.adm.domain.entity.User;
import br.com.agi.adm.domain.mapper.TicketMapper;
import br.com.agi.adm.repository.AssigneeRepository;
import br.com.agi.adm.repository.TicketRepository;
import br.com.agi.adm.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final AssigneeRepository assigneeRepository;
    private final UserRepository userRepository;
    private final TicketMapper ticketMapper;

    @Transactional
    public TicketResponseDTO save(TicketRequestDTO dto) {
        var entity = ticketMapper.toEntity(dto);


        User user = userRepository.findByEmail(entity.getUser().getEmail())
                .orElseGet(() -> userRepository.save(entity.getUser()));
        entity.setUser(user);

        Assignee assignee = assigneeRepository.findByName(entity.getAssignee().getName())
                .orElseGet(() -> assigneeRepository.save(entity.getAssignee()));
        entity.setAssignee(assignee);

        var savedTicket = ticketRepository.save(entity);

        return ticketMapper.toDto(savedTicket);
    }
}
