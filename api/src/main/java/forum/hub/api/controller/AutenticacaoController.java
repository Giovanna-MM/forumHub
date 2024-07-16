package forum.hub.api.controller;

import forum.hub.api.domain.usuario.Usuario;
import forum.hub.api.infra.security.DadosTokenJWT;
import forum.hub.api.infra.security.TokenService;
import forum.hub.api.domain.login.DadosAutenticacao;
import forum.hub.api.domain.login.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    // Dispara o processo de autenticação
    @Autowired // Faz injeçao de dependência do objeto AuthenticationManager
    private AuthenticationManager manager; // Deve ser criado o método AuthenticationManager na Classe SecurityConfigurations

    @Autowired
    private TokenService tokenService;
    @PostMapping
    public ResponseEntity efetuarLoin(@RequestBody @Validated DadosAutenticacao dados) {

        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha()); // Converte meu DTO para o DTO do Spring

        var authentication = manager.authenticate(authenticationToken); // Devolve um objeto que recebe o usuario autenticado no sistema

        var tokenJWT = tokenService.gerarToken((Login) authentication.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT)); // Devolve o token
    }
}
