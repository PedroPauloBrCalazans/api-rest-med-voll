package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.dto.usuario.DadosAutenticacaoDTO;
import med.voll.api.infra.security.TokenService;
import med.voll.api.infra.security.dto.DadosTokenJwtDTO;
import med.voll.api.model.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager; //dispara o processo de autenticação, chama a autenticação service

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacaoDTO dados) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authentication = manager.authenticate(authenticationToken); //representa o objeto que tem a autenticação e dentro tem o usuario

        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJwtDTO(tokenJWT));
    }
}

//DTO do spring security = UsernamePasswordAuthenticationToken