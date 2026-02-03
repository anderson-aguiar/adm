package br.com.agi.adm.domain.mapper;

import br.com.agi.adm.domain.dto.request.UserRequestDTO;
import br.com.agi.adm.domain.dto.response.UserResponseDTO;
import br.com.agi.adm.domain.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntit(UserRequestDTO dto){
        if (dto == null){
            return null;
        }

        User user = new User();
        user.setName(dto.name());
        user.setEmail(dto.email());
        return user;
    }

    public UserResponseDTO toDTO(User user){
        if (user == null){
            return null;
        }

        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }
}
