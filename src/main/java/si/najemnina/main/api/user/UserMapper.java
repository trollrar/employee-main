package si.najemnina.main.api.user;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import si.najemnina.main.api.user.dto.UserDTO;
import si.najemnina.main.api.user.dto.UserLoginDTO;
import si.najemnina.main.api.user.dto.UserRegisterDTO;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDTO(User user);

    void update(@MappingTarget User user, UserLoginDTO dto);

    void update(@MappingTarget User user, UserRegisterDTO dto);
}
