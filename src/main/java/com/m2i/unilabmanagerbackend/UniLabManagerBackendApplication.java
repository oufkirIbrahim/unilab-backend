package com.m2i.unilabmanagerbackend;

import com.m2i.unilabmanagerbackend.entity.Role;
import com.m2i.unilabmanagerbackend.entity.User;
import com.m2i.unilabmanagerbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class UniLabManagerBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(UniLabManagerBackendApplication.class, args);
	}

	/*@Autowired
	private UserRepository userRepository;
*/
	/*@Override
	public void run(String... args) throws Exception {
		User u = userRepository.findByRole(Role.USER);
		if(u == null){
			User user = new User();
			user.setEmail("user@gmail.com");
			user.setFirstname("user");
			user.setLastname("user");
			user.setRole(Role.USER);
			user.setPassword(new BCryptPasswordEncoder().encode("userpassword"));
			userRepository.save(user);
		}*/
	}

