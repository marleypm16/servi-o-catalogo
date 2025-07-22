package catalogo.project.Servico.Catalogo.Services;

import catalogo.project.Servico.Catalogo.DTO.ProdutoRequestDTO;
import catalogo.project.Servico.Catalogo.DTO.ProdutoResponseDTO;
import catalogo.project.Servico.Catalogo.Entity.Produto;
import catalogo.project.Servico.Catalogo.Repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProdutoService {
    @Autowired ProdutoRepository produtoRepository;

    public ProdutoResponseDTO novoProduto(ProdutoRequestDTO produto) {
        Produto produtoEntity = new Produto();
        produtoEntity.setDescricao(produto.descricao());
        produtoEntity.setPreco(produto.preco());
        produtoEntity.setQuantidade(produto.quantidade());
        produtoEntity.setNome(produto.nome());
        if (produtoEntity.getPreco().compareTo(BigDecimal.ZERO) < 0 || produtoEntity.getPreco().compareTo(BigDecimal.ZERO) == 0) {
            throw new RuntimeException("Informe um preço válido para cadastrar");
        }

        if (produtoEntity.getQuantidade() < 0) {
            throw new RuntimeException("Informe uma quantidade válida para cadastrar");

        }
        Produto produtoNovo =  produtoRepository.save(produtoEntity);
        return new ProdutoResponseDTO(produtoNovo);
    };

    public List<ProdutoResponseDTO> listarProdutos() {
        List<Produto> produtos = produtoRepository.findAll();
        return  produtos.stream().map(ProdutoResponseDTO::new).collect(Collectors.toList());
    }

    public Optional<ProdutoResponseDTO> buscarProdutoPorId(UUID id) {
        if (id == null) {
            throw new RuntimeException("Informe um produto pelo ID");
        }
        Optional<Produto> produto = produtoRepository.findById(id);
        if (produto.isPresent()) {
            return Optional.of(new ProdutoResponseDTO(produto.get()));
        }
        return Optional.empty();
    }
}
