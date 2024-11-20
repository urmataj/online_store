package com.example.online_store.component;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordEncoderConfiguration {

    // Создаём бины для BCryptPasswordEncoder
    @Bean
    public PasswordEncoder bCryptPassword() {
        return new BCryptPasswordEncoder();  // Используем BCrypt для шифрования паролей
    }
}
