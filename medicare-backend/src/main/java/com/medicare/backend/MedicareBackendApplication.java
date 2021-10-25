package com.medicare.backend;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@ComponentScan({ "com.medicare.backend", "com.medicare.backend.model", "com.medicare.backend.services" })
@EnableJpaRepositories("com.medicare.backend.repository")
public class MedicareBackendApplication implements CommandLineRunner {

	private static Logger LOG = LoggerFactory.getLogger(MedicareBackendApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(MedicareBackendApplication.class, args);
	}

	
	
	/*@Configuration
	@EnableWebMvc
	public class WebConfig extends WebMvcConfigurerAdapter {

		@Override
		public void addCorsMappings(CorsRegistry registry) {
			registry.addMapping("/**");
		}
	}*/
	
	
	/*@Configuration
	public class MyConfiguration {

	    @SuppressWarnings("deprecation")
		@Bean
	    public WebMvcConfigurer corsConfigurer() {
	        return new WebMvcConfigurerAdapter() {
	            @Override
	            public void addCorsMappings(CorsRegistry registry) {
	                registry.addMapping("/**");
	            }
	        };
	    }
	}*/
	
	
	@Override
	public void run(String... args) throws Exception {
	
		
		//============================================================
		//>  THIS IS JSUT A CONVIENANCE PROCEEDURE
		//>  TO BE EXECUTED BY THE DEVELOPER (IF NEEDED)
		//>  TO QUICKLY GENERATE AN ADMIN DATABASE ROLE/RECORD
		//============================================================
		/*LOG.info("EXECUTING : BEGIN COMMAND LINE RUNNER");
		User user = new User();
		user.setFirstName("admin");
		user.setLastName("admin");
		user.setEmail("admin@admin.com");
		user.setPassword(this.bCryptPasswordEncoder.encode("secret"));
		user.setUsername("admin");
		user.setPhone("0000000000");
		user.setEnabled(true);

		Role role = new Role();
		role.setRoleId(111);
		role.setRoleName("ROLE_ADMIN");

		Set<UserRole> userRoleSet = new HashSet<>();
		UserRole userRole = new UserRole();
		userRole.setRole(role);
		userRole.setUser(user);

		userRoleSet.add(userRole);

		User user1 = this.userService.createUser(user, userRoleSet);
		System.out.println("admin name : " + user1.getUsername());
		
		LOG.info("EXECUTING : END COMMAND LINE RUNNER");*/
		
		

		for (int i = 0; i < args.length; ++i) {
			LOG.info("args[{}]: {}", i, args[i]);
		}
	}
}
