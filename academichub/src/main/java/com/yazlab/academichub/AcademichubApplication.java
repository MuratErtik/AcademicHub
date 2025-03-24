package com.yazlab.academichub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.yazlab.academichub.entities.Role;
import com.yazlab.academichub.entities.User;
import com.yazlab.academichub.domain.USER_ROLE;
import com.yazlab.academichub.repository.RoleRepository;
import com.yazlab.academichub.repository.UserRepository;

@SpringBootApplication
public class AcademichubApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcademichubApplication.class, args);
	}

	@Bean
	public org.springframework.boot.CommandLineRunner dataLoader(
			RoleRepository roleRepository,
			UserRepository userRepository,
			PasswordEncoder passwordEncoder
	) {
		return args -> {
			Role adminRole = roleRepository.findByName("ROLE_ADMIN")
					.orElseGet(() -> roleRepository.save(new Role(null, "ROLE_ADMIN")));

			if (userRepository.findByEmail("admin@admin.com") == null) {
				User admin = new User();
				admin.setTcNo("11111111111");
				admin.setName("Admin");
				admin.setLastname("User");
				admin.setEmail("admin@admin.com");
				admin.setPassword(passwordEncoder.encode("admin123"));
				admin.setUserRole(USER_ROLE.ADMIN);
				admin.setRole(adminRole);
				admin.setMobileNo(5551234567L);
				admin.setDepartment(null);

				userRepository.save(admin);
			}
		};
	}
}