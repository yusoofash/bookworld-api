package com.bookworld.api;

import com.bookworld.api.admin.Admin;
import com.bookworld.api.admin.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BookworldApiApplication implements CommandLineRunner {

	@Value("${admin.email}")
	private String ADMIN_EMAIL;

	@Value("${admin.password}")
	private String ADMIN_PASSWORD;


	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(BookworldApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Admin admin = new Admin();
		admin.setEmail(ADMIN_EMAIL);
		admin.setPassword(passwordEncoder.encode(ADMIN_PASSWORD));

		adminRepository.save(admin);
	}
}
