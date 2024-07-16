package forum.hub.api.domain.validacoes;

import forum.hub.api.domain.topico.DadosPostagemTopico;

public interface ValidadorPostagemDeTopico {

    void validar(DadosPostagemTopico dados);
}
