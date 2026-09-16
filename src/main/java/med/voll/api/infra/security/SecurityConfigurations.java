package med.voll.api.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.
                        sessionCreationPolicy(SessionCreationPolicy.STATELESS)).authorizeHttpRequests(
                        auth -> auth.requestMatchers(HttpMethod.POST, "/login") //liberando essa URL
                                .permitAll().anyRequest().authenticated())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class) //verificando o meu filter 1° depois do spring
                .build();
    } /// configurar processos de autenticações e autorizações

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager(); //sabe criar o objeto authenticationManager
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

//csrf = proteção contra Cross-site request forgery, pq desabilitar ? pq vamos trabalhar via tokens o proprio token já uma proteção contra ataques
//sessionManagement - desabilitar o processo de autenticação que é dado um form e a autenticação e STATEFULL
//@BEAN = serve para expor o retorno desse método

//desabilitando o processo padrão do spring security, de controle de autenticação, baseado em form, sessão e cookie

//o Bean serve para exportar uma classe para o spring, fazendo com que ele consiga carregá-la e realize a sua injeção de dependência em outras classes.

///anyRequest().authenticated() as demais requisições estão liberadas