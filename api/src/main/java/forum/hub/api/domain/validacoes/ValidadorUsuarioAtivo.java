package forum.hub.api.domain.validacoes;

import forum.hub.api.domain.curso.CursoRepository;
import forum.hub.api.domain.topico.DadosPostagemTopico;
import forum.hub.api.infra.exception.ValidacaoException;
import forum.hub.api.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class ValidadorUsuarioAtivo implements ValidadorPostagemDeTopico {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CursoRepository cursoRepository;
    public void validar(DadosPostagemTopico dados) {
        if (dados.idAutor() == null) {
            return;
        }
        var usuarioEstaAtivo = usuarioRepository.findAtivoById(dados.idAutor());
        if (!usuarioEstaAtivo) {
            throw new ValidacaoException("Tópico não pode ser criado com usuário inativo!");
        }
    }
}
