package forum.hub.api.controller;

import forum.hub.api.domain.topico.*;
import forum.hub.api.domain.usuario.DadosAtualizacaoUsuario;
import forum.hub.api.domain.usuario.DadosDetalhamentoUsuario;
import forum.hub.api.domain.usuario.DadosListagemUsuario;
import forum.hub.api.domain.usuario.UsuarioRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("topicos")
@SecurityRequirement(name = "bearer-key")

public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private PostagemDeTopicos postagem;

    @PostMapping
    @Transactional
    public ResponseEntity postar(@RequestBody @Valid DadosPostagemTopico dados) {

        var dto = postagem.postar(dados);

        System.out.println(dados);

       // postagem.postar(dados);

        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity <Page<DadosListagemTopico>> listar(@PageableDefault(size = 10, sort = {"dataCriacao"}) Pageable paginacao) {
        var page = topicoRepository.findAll(paginacao).map(DadosListagemTopico::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoTopico dados) {
        var topico = topicoRepository.getReferenceById(dados.id());
        topico.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
    }

    // Exclusão física de Tópico
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        // Verifica se o tópico do Id informado existe
        if (!topicoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        // Realiza a exclusão do tópico
        topicoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var topico = topicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
    }
}
