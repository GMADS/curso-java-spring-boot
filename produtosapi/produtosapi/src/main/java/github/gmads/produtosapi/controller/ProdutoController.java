package github.gmads.produtosapi.controller;

import github.gmads.produtosapi.model.Produto;
import github.gmads.produtosapi.repository.ProdutoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("produtos")
public class ProdutoController {
    private ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @PostMapping
    public Produto salvar(@RequestBody Produto produto){
        System.out.println("Produto recebido: " + produto);
        produto.setId(UUID.randomUUID().toString());
        produtoRepository.save(produto);
        return produto;
    }

    @GetMapping("/{id}")
    public Produto obterPorId(@PathVariable String id){
        Optional<Produto> produto = produtoRepository.findById(id);
        return produto.orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deletarPorId(@PathVariable String id){
        produtoRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public void atualizaProduto(@PathVariable("id") String id,
                                @RequestBody Produto produtoAtualizado){
        produtoAtualizado.setId(id);
        produtoRepository.save(produtoAtualizado);
    }

    @GetMapping
    public List<Produto> ObterByParameter(@RequestParam("nome") String nome,
                                          @RequestParam("descricao") String descricao){

        if(nome != null && !nome.isBlank()){
            return produtoRepository.findByNome(nome);
        }

        if(descricao != null && !descricao.isBlank()){
            return produtoRepository.findByDescricao(descricao);
        }

        return new ArrayList<Produto>();
    }
}
