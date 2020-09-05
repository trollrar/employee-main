package si.najemnina.main.api.user;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import si.najemnina.main.api.user.dto.UserDTO;
import si.najemnina.main.api.user.dto.UserLoginDTO;
import si.najemnina.main.api.user.dto.UserRegisterDTO;
import si.najemnina.main.api.user.dto.UserJwtDTO;
import si.najemnina.main.jwt.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getUsers() {
        return ResponseEntity.ok(userRepository.findAll().stream().map(user -> userMapper.toDTO(user)).collect(Collectors.toList()));
    }

    @GetMapping("public/become")
    public String becomeAdmin(@RequestParam String username) {

        User user = userService.loadUserByUsername(username);
        user.role = User.Role.ADMIN;
        userRepository.save(user);
        return "ok";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin")
    public String amIAdmin(HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        auth.getName();

        return "Congratz! you are admin";
    }

    @PostMapping("register")
    public String register(@RequestBody UserRegisterDTO dto) {
        User user = new User();
        userMapper.update(user, dto);

        userRepository.save(user);
        return "";
    }

    @PostMapping("login")
    public ResponseEntity<UserJwtDTO> login(@RequestBody UserLoginDTO dto) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.username, dto.password)
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        User user = userService.loadUserByUsername(dto.username);

        String token = jwtUtil.generateToken(user);

        return ResponseEntity.ok(new UserJwtDTO(token, userMapper.toDTO(user)));
    }
}
