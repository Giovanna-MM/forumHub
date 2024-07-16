package forum.hub.api.domain.curso;

public record DadosDetalhamentoCurso(Long id, String nome, Categoria categoria, Boolean ativo) {

    public DadosDetalhamentoCurso(Curso curso) {

        this(curso.getId(), curso.getNome(), curso.getCategoria(), curso.getAtivo());
    }
}
