package catalogo.project.Servico.Catalogo.Controller;

import catalogo.project.Servico.Catalogo.Entity.Produto;
import catalogo.project.Servico.Catalogo.Services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RestController
@RequestMapping(name = "/produto")
public class ProdutoController {
    @Autowired
    ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<Produto>> listarProdutos(){
        List<Produto> produtos = produtoService.listarProdutos();
        return ResponseEntity.ok(produtos);
    }

    @PostMapping
    public Produto criarProduto(Produto produto){
        return produtoService.novoProduto(produto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarDetalhesDoProduto(@PathVariable UUID id) {
        Optional<Produto> produto = produtoService.buscarProdutoPorId(id);
        if (produto.isPresent()) {
            return ResponseEntity.ok(produto.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
