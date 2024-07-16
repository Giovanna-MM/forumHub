package forum.hub.api.domain.topico;

import forum.hub.api.domain.curso.CursoRepository;
import forum.hub.api.domain.usuario.UsuarioRepository;
import forum.hub.api.domain.validacoes.ValidadorPostagemDeTopico;
import forum.hub.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostagemDeTopicos {

    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private List<ValidadorPostagemDeTopico> validadores;

    public DadosDetalhamentoTopico postar(DadosPostagemTopico dados) {

        if (!usuarioRepository.existsById(dados.idAutor())) {
            throw new ValidacaoException("Id do usuário informado não existe!");
        }

        if (!cursoRepository.existsById(dados.idCurso())) {
            throw new ValidacaoException("Id do curso informado não existe!");
        }

        // Injeta todos os validadores
        validadores.forEach(v -> v.validar(dados));

        var autor = usuarioRepository.getReferenceById(dados.idAutor());
        var curso = cursoRepository.getReferenceById(dados.idCurso());
        var topico = new Topico(null, dados.titulo(), dados.mensagem(), LocalDateTime.now(), StatusTopico.NAO_RESPONDIDO, autor, curso);
        topicoRepository.save(topico);

        return new DadosDetalhamentoTopico(topico);
    }
}
