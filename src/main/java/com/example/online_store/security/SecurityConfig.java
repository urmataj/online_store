package com.example.online_store.security;

import com.example.online_store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder bCryptPassword;

    private static final String[] AUTH_WHITELIST = {
            "/trusted/**",           // Доступ без авторизации
            "/api/auth/**",          // Авторизация, регистрация, восстановление пароля
            "/api/products/**",      // Доступ к данным товаров
            "/api/orders/**"         // Доступ к заказам
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)

                .authorizeRequests(authorize -> authorize
                        .requestMatchers(AUTH_WHITELIST).permitAll() // Разрешаем доступ к определённым URL
                        .anyRequest().authenticated() // Для остальных запросов нужна авторизация
                )
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Stateless сессии, если не используем сессии на сервере
                .authenticationProvider(authenticationProvider()) // Настройка аутентификации
                .httpBasic(httpSecurityHttpBasicConfigurer ->
                        httpSecurityHttpBasicConfigurer.realmName("ONLINE_STORE") // Для REST API Basic аутентификации
                );

        return http.build();
    }

    private AuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userService); // Сервис для загрузки пользователей
        authenticationProvider.setPasswordEncoder(bCryptPassword); // Шифрование паролей
        return authenticationProvider;
    }
}
