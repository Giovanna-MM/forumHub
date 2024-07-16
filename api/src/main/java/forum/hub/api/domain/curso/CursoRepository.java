package forum.hub.api.domain.curso;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    Page<Curso> findAllByAtivoTrue(Pageable paginacao);


    @Query("""
            SELECT c.ativo
            FROM Curso c
            WHERE
            c.id = :idCurso
            """)

    Boolean findAtivoById(Long idCurso);
}