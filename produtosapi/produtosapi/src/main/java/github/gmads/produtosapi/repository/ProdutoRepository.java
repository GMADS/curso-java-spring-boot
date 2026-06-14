package github.gmads.produtosapi.repository;

import github.gmads.produtosapi.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProdutoRepository extends JpaRepository<Produto, String> {

    List<Produto> findByNome(String nome);
    List<Produto> findByDescricao(String descricao);
}
