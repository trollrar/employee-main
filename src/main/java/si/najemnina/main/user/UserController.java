package si.najemnina.main.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
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
