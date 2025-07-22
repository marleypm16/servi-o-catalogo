package catalogo.project.Servico.Catalogo.Services;

import catalogo.project.Servico.Catalogo.Entity.Produto;
import catalogo.project.Servico.Catalogo.Repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class ProdutoService {
    @Autowired ProdutoRepository produtoRepository;

    public Produto novoProduto(Produto produto) {

        if (produto.getPreco() < 0 || produto.getPreco() == 0) {
            throw new RuntimeException("Informe um preço válido para cadastrar");
        }
        if (produto.getQuantidade() < 0) {
            throw new RuntimeException("Informe uma quantidade válida para cadastrar");

        }
        return produtoRepository.save(produto);
    };

    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> buscarProdutoPorId(UUID id) {
        if (id == null) {
            throw new RuntimeException("Informe um produto pelo ID");
        }
        return produtoRepository.findById(id);
    }
}
