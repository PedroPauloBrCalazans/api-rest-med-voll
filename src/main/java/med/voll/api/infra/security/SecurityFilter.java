package med.voll.api.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        filterChain.doFilter(request, response); ///necessário para chamar os próximos filtros na aplicação/caso queira bloquear a requisição retirar o código desta linha.
    }
}


///@Component é utilizado para que o Spring carregue uma classe/componente genérico;
///OncePerRequestFilter classe do spring e garante que será executada apenas uma vez, para cada requisição;
///filterChain = representa a cadeia de filtros na aplicação.