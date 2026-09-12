package med.voll.api.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        var tokenJWT = recuperarToken(request);
        var subject = tokenService.getSubject(tokenJWT); //validando se o token está correto





        filterChain.doFilter(request, response); ///necessário para chamar os próximos filtros na aplicação/caso queira bloquear a requisição retirar o código desta linha.
    }

    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null) {
            throw new RuntimeException("Token JWT não enviado no cabeçalho Authorization!");
        }

        return  authorizationHeader.replace("Bearer ", "");
    }
}


///@Component é utilizado para que o Spring carregue uma classe/componente genérico;
///OncePerRequestFilter classe do spring e garante que será executada apenas uma vez, para cada requisição;
///filterChain = representa a cadeia de filtros na aplicação.