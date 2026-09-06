package med.voll.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import med.voll.api.model.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {

    @Value("${api.security.token.secret}") //anotação para ler do application.properties
    private String secret;

    public String gerarToken(Usuario usuario) {
        try {
            var algoritmo = Algorithm.HMAC256(secret); //senha para fazer a geração do token --- String
            return JWT.create()
                    .withIssuer("API Voll.med") //Identificar a api dona responsavel pela geração do token -- vai dentro do token
                    .withSubject(usuario.getLogin()) //pertence a qual usuário
                    .withClaim("id", usuario.getId()) //id do usuário
                    .withExpiresAt(dataExpiracao()) //pode usar o token por 2hrs
                    .sign(algoritmo);

        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token JWT", exception);
        }
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}


//método para geração do token
//os token que geramos nas apis, é recomendado configurar uma data de expiração para os tokens