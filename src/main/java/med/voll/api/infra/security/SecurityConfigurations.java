package med.voll.api.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.
                        sessionCreationPolicy(SessionCreationPolicy.STATELESS)).build();
    } /// configurar processos de autenticações e autorizações
}

//csrf = proteção contra Cross-site request forgery, pq desabilitar ? pq vamos trabalhar via tokens o proprio token já uma proteção contra ataques
//sessionManagement - desabilitar o processo de autenticação que é dado um form e a autenticação e STATEFULL
//@BEAN = serve para expor o retorno desse método

//desabilitando o processo padrão do spring security, de controle de autenticação, baseado em form, sessão e cookie