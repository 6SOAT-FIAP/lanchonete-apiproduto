package pos.fiap.lanchonete.apiproduto.port;

import pos.fiap.lanchonete.apiproduto.domain.enums.CategoriaEnum;
import pos.fiap.lanchonete.apiproduto.domain.model.DadosProduto;
import pos.fiap.lanchonete.apiproduto.domain.model.entity.Produto;

import java.util.List;
import java.util.Optional;

public interface ProdutoUseCasePort {

    DadosProduto cadastrar(DadosProduto dadosProduto);

    DadosProduto alterar(Long id, DadosProduto dadosProduto);

    Optional<Produto> remover(Long id);

    List<DadosProduto> buscarPorCategoria(CategoriaEnum categoria);
}
