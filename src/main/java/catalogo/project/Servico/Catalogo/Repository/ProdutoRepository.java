package catalogo.project.Servico.Catalogo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProdutoRepository extends JpaRepository<UUID, id> {
}
