package forum.hub.api.domain.validacoes;

import forum.hub.api.domain.curso.CursoRepository;
import forum.hub.api.domain.topico.DadosPostagemTopico;
import forum.hub.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorCursoAtivo implements ValidadorPostagemDeTopico {
    @Autowired
    private CursoRepository cursoRepository;
    public void validar(DadosPostagemTopico dados) {
        if (dados.idCurso() == null) {
            return;
        }

        var cursoEstaAtivo = cursoRepository.findAtivoById(dados.idCurso());
        if (!cursoEstaAtivo) {
            throw new ValidacaoException("Curso informado não está ativo, operação não realizada!");

        }
    }
}
