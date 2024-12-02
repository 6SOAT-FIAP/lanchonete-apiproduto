package pos.fiap.lanchonete.apiproduto.domain.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pos.fiap.lanchonete.apiproduto.adapter.out.mysql.ProdutoMySQLAdapter;
import pos.fiap.lanchonete.apiproduto.domain.model.DadosProduto;
import pos.fiap.lanchonete.apiproduto.domain.model.entity.Produto;
import pos.fiap.lanchonete.apiproduto.domain.enums.CategoriaEnum;
import pos.fiap.lanchonete.apiproduto.domain.model.entity.mapper.ProdutoMapper;
import pos.fiap.lanchonete.apiproduto.port.ProdutoUseCasePort;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProdutoUseCase implements ProdutoUseCasePort {

    private final ProdutoMapper produtoMapper;
    private final ProdutoMySQLAdapter produtoMySQLAdapter;

    @Override
    public DadosProduto cadastrar(DadosProduto dadosProduto) {
        var produto = produtoMapper.fromDadosProduto(dadosProduto);
        produto = produtoMySQLAdapter.cadastrarProduto(produto);
        return produtoMapper.toDadosProduto(produto);
    }

    @Override
    public DadosProduto alterar(Long id, DadosProduto dadosProduto) {
        var produto = produtoMapper.fromDadosProduto(dadosProduto);
        produto = produtoMySQLAdapter.alterarProduto(id, produto);
        return produtoMapper.toDadosProduto(produto);
    }

    @Override
    public Optional<Produto> remover(Long id) {
        var produto = produtoMySQLAdapter.buscarPorId(id);

        if (produto.isEmpty()) {
            return Optional.empty();
        }

        produtoMySQLAdapter.removerProduto(id);

        return produto;
    }

    @Override
    public List<DadosProduto> buscarPorCategoria(CategoriaEnum categoria) {
        var produtoList = produtoMySQLAdapter.buscarProdutoPorCategoria(categoria);
        return produtoMapper.toListDadosProduto(produtoList);
    }

    @Override
    public List<DadosProduto> buscarPorIds(List<Long> ids) {
        var produtos = produtoMySQLAdapter.buscarPorIds(ids);
        var dadosProdutoList = produtoMapper.toListDadosProduto(produtos);
        return dadosProdutoList;
    }

}
