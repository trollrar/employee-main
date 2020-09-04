package si.najemnina.main;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import si.najemnina.main.auth.User;
import si.najemnina.main.auth.UserRepository;

import java.util.stream.Stream;

@SpringBootApplication
public class MainApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserRepository userRepository) {
		return args -> {
			Stream.of("admin", "user").forEach(name -> {
				User user = new User();
				user.username = name;
				user.email = name.toLowerCase() + "@domain.com";
				user.password = name;
				user.role = name.equals("admin") ? User.Role.ADMIN : User.Role.USER;
				userRepository.save(user);
			});
			userRepository.findAll().forEach(System.out::println);
		};
	}

}
