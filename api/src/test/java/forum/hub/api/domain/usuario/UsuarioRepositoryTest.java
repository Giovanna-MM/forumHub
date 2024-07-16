package forum.hub.api.domain.usuario;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest // Anotação utilizada para testar um interface Repository
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // Anotação utilizada para fazer uma configuração do banco de dados de teste
@ActiveProfiles("test") // Diz ao Spring para carregar o application-test.properties

class UsuarioRepositoryTest {

    @Test
    void findById_deveRetoronarUsuarioQuandoIdExiste() {

    }
}