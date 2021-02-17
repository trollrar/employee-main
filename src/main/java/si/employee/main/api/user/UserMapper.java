package si.employee.main.api.user;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import si.employee.main.api.user.dto.UserLoginDTO;
import si.employee.main.api.user.dto.UserDTO;
import si.employee.main.api.user.dto.UserRegisterDTO;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDTO(User user);

    void update(@MappingTarget User user, UserLoginDTO dto);

    void update(@MappingTarget User user, UserRegisterDTO dto);
}
