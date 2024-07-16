package forum.hub.api.controller;

import forum.hub.api.domain.topico.DadosDetalhamentoTopico;
import forum.hub.api.domain.topico.DadosPostagemTopico;
import forum.hub.api.domain.topico.PostagemDeTopicos;
import forum.hub.api.domain.topico.StatusTopico;
import org.aspectj.weaver.ast.Var;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class TopicoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DadosPostagemTopico> dadosPostagemTopicoJson;
    @Autowired
    private JacksonTester<DadosDetalhamentoTopico> dadosDetalhamentoTopicoJson;
    @MockBean
    private PostagemDeTopicos postagemDeTopicos;

    @Test
    @DisplayName("Deverá devolver código HTTP 400 quuando as informações estiverem inválidas")
    @WithMockUser
    void postar_cenario1() throws Exception {
        var response = mvc.perform(post("/topicos"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
    @Test
    @DisplayName("Deverá devolver código HTTP 400 quuando as informações estiverem inválidas")
    @WithMockUser
    void postar_cenario2() throws Exception {

        var titulo = "Titulo_teste";
        var mensagem = "Mensagem_teste";
        var data = LocalDateTime.now();
        var status = StatusTopico.NAO_RESPONDIDO;
        var autor = "Patolino";
        var curso = "Java";

        var dadosDetalhamento = new DadosDetalhamentoTopico(titulo, mensagem, data, status, autor, curso);

        when(postagemDeTopicos.postar(any())).thenReturn(dadosDetalhamento);

        var response = mvc.perform(post("/topicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosPostagemTopicoJson.write(
                                new DadosPostagemTopico(titulo, mensagem, 6l, 7l)
                        ).getJson())
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonEsperado = dadosDetalhamentoTopicoJson.write(
                dadosDetalhamento
        ).getJson();
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }
}