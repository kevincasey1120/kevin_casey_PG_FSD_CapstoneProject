package com.medicare.backend;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.medicare.backend.model.Role;
import com.medicare.backend.model.User;
import com.medicare.backend.model.UserRole;
import com.medicare.backend.services.UserService;

@SpringBootApplication
@ComponentScan({ "com.medicare.backend", "com.medicare.backend.model", "com.medicare.backend.services" })
@EnableJpaRepositories("com.medicare.backend.repository")
public class MedicareBackendApplication implements CommandLineRunner {

	private static Logger LOG = LoggerFactory.getLogger(MedicareBackendApplication.class);

	@Autowired
	private UserService userService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(MedicareBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		LOG.info("EXECUTING : BEGIN COMMAND LINE RUNNER");
		/*User user = new User();
		user.setFirstName("Kevin");
		user.setLastName("Casey");
		user.setEmail("kcaseyp@gmail.com");
		user.setPassword(this.bCryptPasswordEncoder.encode("secret"));
		user.setUsername("admin");
		user.setPhone("9043281385");
		user.setEnabled(true);

		Role role = new Role();
		role.setRoleId(101);
		role.setRoleName("ADMIN");

		Set<UserRole> userRoleSet = new HashSet<>();
		UserRole userRole = new UserRole();
		userRole.setRole(role);
		userRole.setUser(user);

		userRoleSet.add(userRole);

		User user1 = this.userService.createUser(user, userRoleSet);
		System.out.println("user name : " + user1.getUsername());*/

		LOG.info("EXECUTING : END COMMAND LINE RUNNER");

		for (int i = 0; i < args.length; ++i) {
			LOG.info("args[{}]: {}", i, args[i]);
		}

	}

}
