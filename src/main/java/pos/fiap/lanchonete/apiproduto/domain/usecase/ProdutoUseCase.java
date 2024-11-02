package pos.fiap.lanchonete.apiproduto.domain.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pos.fiap.lanchonete.apiproduto.domain.model.DadosProduto;
import pos.fiap.lanchonete.apiproduto.domain.model.entity.Produto;
import pos.fiap.lanchonete.apiproduto.domain.enums.CategoriaEnum;
import pos.fiap.lanchonete.apiproduto.domain.model.entity.mapper.ProdutoMapper;
import pos.fiap.lanchonete.apiproduto.port.ProdutoMongoAdapterPort;
import pos.fiap.lanchonete.apiproduto.port.ProdutoUseCasePort;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProdutoUseCase implements ProdutoUseCasePort {

    private final ProdutoMapper produtoMapper;
    private final ProdutoMongoAdapterPort produtoMongoAdapterPort;

    @Override
    public DadosProduto cadastrar(DadosProduto dadosProduto) {
        var produto = produtoMapper.fromDadosProduto(dadosProduto);
        produto = produtoMongoAdapterPort.cadastrarProduto(produto);
        return produtoMapper.toDadosProduto(produto);
    }

    @Override
    public DadosProduto alterar(String id, DadosProduto dadosProduto) {
        var produto = produtoMapper.fromDadosProduto(dadosProduto);
        produto = produtoMongoAdapterPort.alterarProduto(id, produto);
        return produtoMapper.toDadosProduto(produto);
    }

    @Override
    public Optional<Produto> remover(String id) {
        var produto = produtoMongoAdapterPort.buscarPorId(id);

        if (produto.isEmpty()) {
            return Optional.empty();
        }

        produtoMongoAdapterPort.removerProduto(id);

        return produto;
    }

    @Override
    public List<DadosProduto> buscarPorCategoria(CategoriaEnum categoria) {
        var produtoList = produtoMongoAdapterPort.buscarProdutoPorCategoria(categoria);
        return produtoMapper.toListDadosProduto(produtoList);
    }
}
