package tech.obss.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class CryptoConfig {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new MyPasswordEncoder();
    }
}
