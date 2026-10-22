package com.example.BackendLearning;

import com.example.BackendLearning.Repository.AdminRepository;
import com.example.BackendLearning.model.Admin;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminInitializer implements CommandLineRunner {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminInitializer(
            AdminRepository adminRepository,
            PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        String username = System.getenv("APP_ADMIN_USERNAME");
        String password = System.getenv("APP_ADMIN_PASSWORD");

        if ((username == null || username.isBlank())
                && (password == null || password.isBlank())) {
            return;
        }

        if (username == null || username.isBlank()
                || password == null || password.isBlank()) {
            throw new IllegalStateException(
                    "Both APP_ADMIN_USERNAME and APP_ADMIN_PASSWORD are required.");
        }

        username = username.trim();

        if (adminRepository.findByUsername(username).isPresent()) {
            System.out.println(
                    "Admin account already exists. Password was not changed.");
            return;
        }

        Admin admin = new Admin(
                username,
                passwordEncoder.encode(password));

        adminRepository.save(admin);

        System.out.println(
                "Initial application admin account created successfully.");
    }
}