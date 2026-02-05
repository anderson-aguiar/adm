package br.com.agi.adm.domain.service;

import br.com.agi.adm.domain.dto.request.UserRequestDTO;
import br.com.agi.adm.domain.dto.response.UserResponseDTO;
import br.com.agi.adm.domain.entity.User;
import br.com.agi.adm.domain.mapper.UserMapper;
import br.com.agi.adm.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserMapper userMapper;

    private final UserRepository userRepository;

    @Transactional
    public UserResponseDTO save(UserRequestDTO dto) {
        User user = userMapper.toEntit(dto);
        userRepository.save(user);

        return userMapper.toDTO(user);
    }

}
