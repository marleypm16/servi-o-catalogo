package catalogo.project.Servico.Catalogo.Controller;

import catalogo.project.Servico.Catalogo.DTO.ProdutoRequestDTO;
import catalogo.project.Servico.Catalogo.DTO.ProdutoResponseDTO;
import catalogo.project.Servico.Catalogo.Entity.Produto;
import catalogo.project.Servico.Catalogo.Services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping( "/produto")
public class ProdutoController {
    @Autowired
    ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> listarProdutos(){
        List<ProdutoResponseDTO> produtos = produtoService.listarProdutos();
        return ResponseEntity.ok(produtos);
    }

    @PostMapping
    public ProdutoResponseDTO criarProduto(@Validated @RequestBody ProdutoRequestDTO produto){
        return produtoService.novoProduto(produto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> buscarDetalhesDoProduto(@PathVariable UUID id) {
        Optional<ProdutoResponseDTO> produto = produtoService.buscarProdutoPorId(id);
        if (produto.isPresent()) {
            return ResponseEntity.ok(produto.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
