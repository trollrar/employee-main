package si.najemnina.main.user;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value="Main API", description="Operations pertaining to EVERYTHING")
@RequestMapping("api")
public class UserController {

    // standard constructors
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<UserModel> getUsers() {
        return (List<UserModel>) userRepository.findAll();
    }

    @PostMapping("/users")
    void addUser(@RequestBody UserModel user) {
        userRepository.save(user);
    }
}
