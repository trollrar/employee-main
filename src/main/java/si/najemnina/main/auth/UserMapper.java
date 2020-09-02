package si.najemnina.main.auth;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import si.najemnina.main.auth.dto.UserDTO;
import si.najemnina.main.auth.dto.UserLoginDTO;
import si.najemnina.main.auth.dto.UserRegisterDTO;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDTO(User user);

    void update(@MappingTarget User user, UserLoginDTO dto);

    void update(@MappingTarget User user, UserRegisterDTO dto);
}
