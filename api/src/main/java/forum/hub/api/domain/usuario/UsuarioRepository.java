package forum.hub.api.domain.usuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Page<Usuario> findAllByAtivoTrue(Pageable paginacao);

    @Query("""
            SELECT u.ativo
            FROM Usuario u
            WHERE 
            u.id = :idAutor
            
            """)

    Boolean findAtivoById(Long idAutor);
}

