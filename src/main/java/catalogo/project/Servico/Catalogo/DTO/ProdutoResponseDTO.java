package catalogo.project.Servico.Catalogo.DTO;

import catalogo.project.Servico.Catalogo.Entity.Produto;

import java.math.BigDecimal;
import java.util.UUID;

public record ProdutoResponseDTO(
        UUID produto_id,
        String descricao,
        String nome,
        Integer quantidade,
        BigDecimal preco
)  {
    public ProdutoResponseDTO(Produto produto) {
        this(produto.getProdutoId(),produto.getDescricao(),produto.getNome(),produto.getQuantidade(),produto.getPreco());
    }
}
