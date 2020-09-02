package si.najemnina.main.auth;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import si.najemnina.main.auth.dto.UserDTO;
import si.najemnina.main.auth.dto.UserLoginDTO;
import si.najemnina.main.auth.dto.UserRegisterDTO;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api(value="Main API", description="Operations pertaining to EVERYTHING")
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/users")
    public List<UserDTO> getUsers() {
        return userRepository.findAll().stream().map(user -> userMapper.toDTO(user)).collect(Collectors.toList());
    }

    @PostMapping("/register")
    void register(@RequestBody UserRegisterDTO dto) {
        User user = new User();
        userMapper.update(user, dto);

        userRepository.save(user);
    }

    @PostMapping("/login")
    void login(@RequestBody UserLoginDTO dto) {
        User user = userRepository.findByName(dto.name);
    }
}
