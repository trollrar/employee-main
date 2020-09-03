package si.najemnina.main.auth;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import si.najemnina.main.auth.dto.UserDTO;
import si.najemnina.main.auth.dto.UserLoginDTO;
import si.najemnina.main.auth.dto.UserRegisterDTO;
import si.najemnina.main.auth.jwt.JwtTokenDTO;
import si.najemnina.main.auth.jwt.JwtUtil;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api(value = "Main API", description = "Operations pertaining to EVERYTHING")
@RequestMapping("/api")
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
    public List<UserDTO> getUsers() {
        return userRepository.findAll().stream().map(user -> userMapper.toDTO(user)).collect(Collectors.toList());
    }

    @PostMapping("/register")
    public void register(@RequestBody UserRegisterDTO dto) {
        User user = new User();
        userMapper.update(user, dto);

        userRepository.save(user);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtTokenDTO> login(@RequestBody UserLoginDTO dto) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.username, dto.password)
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        UserDetails user = userService.loadUserByUsername(dto.username);

        String token = jwtUtil.generateToken(user);

        return ResponseEntity.ok(new JwtTokenDTO(token));
    }
}
