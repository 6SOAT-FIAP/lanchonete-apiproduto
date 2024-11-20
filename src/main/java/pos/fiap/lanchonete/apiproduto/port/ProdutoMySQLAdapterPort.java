package pos.fiap.lanchonete.apiproduto.port;

import pos.fiap.lanchonete.apiproduto.domain.enums.CategoriaEnum;
import pos.fiap.lanchonete.apiproduto.domain.model.entity.Produto;

import java.util.List;
import java.util.Optional;

public interface ProdutoMySQLAdapterPort {

    Produto cadastrarProduto(Produto produto);

    Produto alterarProduto(Long id, Produto produto);

    void removerProduto(Long id);

    List<Produto> buscarProdutoPorCategoria(CategoriaEnum categoria);

    Optional<Produto> buscarPorId(Long id);
}
