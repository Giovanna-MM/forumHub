package forum.hub.api.domain.topico;

import jakarta.transaction.Status;

import java.time.LocalDateTime;

public record DadosDetalhamentoTopico(String titulo, String mensagem, LocalDateTime dataCriacao, StatusTopico status, String autor,String curso) {

    public DadosDetalhamentoTopico(Topico topico) {
        this(topico.getTitulo(), topico.getMensagem(), topico.getDataCriacao(), topico.getStatus(), topico.getAutor().getNome(), topico.getCurso().getNome());
    }
}
