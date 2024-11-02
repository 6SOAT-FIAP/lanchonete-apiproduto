package pos.fiap.lanchonete.apiproduto.port;

import pos.fiap.lanchonete.apiproduto.domain.enums.CategoriaEnum;
import pos.fiap.lanchonete.apiproduto.domain.model.entity.Produto;

import java.util.List;
import java.util.Optional;

public interface ProdutoMongoAdapterPort {

    Produto cadastrarProduto(Produto produto);

    Produto alterarProduto(String id, Produto produto);

    void removerProduto(String id);

    List<Produto> buscarProdutoPorCategoria(CategoriaEnum categoria);

    Optional<Produto> buscarPorId(String id);
}