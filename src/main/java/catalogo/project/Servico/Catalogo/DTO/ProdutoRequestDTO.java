package catalogo.project.Servico.Catalogo.DTO;

import catalogo.project.Servico.Catalogo.Entity.Produto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

public record ProdutoRequestDTO(
        String nome,

        String descricao,

        Integer quantidade,

        BigDecimal preco
){

}