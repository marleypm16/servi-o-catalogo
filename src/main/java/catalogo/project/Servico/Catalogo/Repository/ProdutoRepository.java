package catalogo.project.Servico.Catalogo.Repository;

import catalogo.project.Servico.Catalogo.Entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProdutoRepository extends JpaRepository<Produto, UUID> {
}
