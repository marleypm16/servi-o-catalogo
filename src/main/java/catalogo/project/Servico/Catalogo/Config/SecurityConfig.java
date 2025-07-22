package catalogo.project.Servico.Catalogo.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. DESABILITA a proteção CSRF (A SOLUÇÃO PARA O SEU PROBLEMA)
                .csrf(csrf -> csrf.disable())

                // 2. DEFINE as regras de autorização para os endpoints
                .authorizeHttpRequests(auth -> auth
                        // Permite que qualquer requisição seja feita, desde que o usuário esteja autenticado
                        .anyRequest().authenticated()
                )

                // 3. HABILITA a autenticação básica (HTTP Basic)
                .httpBasic(withDefaults());

        return http.build();
    }
}
