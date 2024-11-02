package pos.fiap.lanchonete.apiproduto.adapter.out.mongo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pos.fiap.lanchonete.apiproduto.domain.enums.CategoriaEnum;
import pos.fiap.lanchonete.apiproduto.adapter.out.mongo.entities.mapper.ProdutoEntityMapper;
import pos.fiap.lanchonete.apiproduto.adapter.out.mongo.repository.ProdutoRepository;
import pos.fiap.lanchonete.apiproduto.domain.model.entity.Produto;
import pos.fiap.lanchonete.apiproduto.port.ProdutoMongoAdapterPort;

import java.util.List;
import java.util.Optional;

import static pos.fiap.lanchonete.apiproduto.utils.Constantes.FIM;
import static pos.fiap.lanchonete.apiproduto.utils.Constantes.INICIO;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProdutoMongoAdapter implements ProdutoMongoAdapterPort {
    private static final String SERVICE_NAME = "MongoAdapter";
    private static final String STRING_LOG_FORMAT = "%s_%s_%s {}";
    private final ProdutoRepository produtoRepository;
    private final ProdutoEntityMapper produtoEntityMapper;


    @Override
    public Produto cadastrarProduto(Produto produto) {
        var methodName = "cadastrarProduto";
        log.info(String.format(STRING_LOG_FORMAT, SERVICE_NAME, methodName, INICIO), produto);

        var produtoEntity = produtoEntityMapper.toEntity(null, produto);
        produtoEntity = produtoRepository.save(produtoEntity);

        var produtoSaved = produtoEntityMapper.toProduto(produtoEntity);
        log.info(String.format(STRING_LOG_FORMAT, SERVICE_NAME, methodName, FIM), produtoEntity);
        return produtoSaved;
    }

    @Override
    public Produto alterarProduto(String id, Produto produto) {
        var methodName = "alterarProduto";
        log.info(String.format(STRING_LOG_FORMAT, SERVICE_NAME, methodName, INICIO), produto);

        var produtoEntity = produtoEntityMapper.toEntity(id, produto);
        produtoEntity = produtoRepository.save(produtoEntity);

        var produtoSaved = produtoEntityMapper.toProduto(produtoEntity);
        log.info(String.format(STRING_LOG_FORMAT, SERVICE_NAME, methodName, FIM), produtoEntity);
        return produtoSaved;
    }

    @Override
    public void removerProduto(String id) {
        var methodName = "removerProduto";
        log.info(String.format(STRING_LOG_FORMAT, SERVICE_NAME, methodName, INICIO), id);

        var produtoEntity = produtoRepository.findById(id);
        produtoRepository.delete(produtoEntity.get());

        log.info(String.format(STRING_LOG_FORMAT, SERVICE_NAME, methodName, FIM), id);
    }

    @Override
    public List<Produto> buscarProdutoPorCategoria(CategoriaEnum categoria) {
        var methodName = "buscarProdutoPorCategoria";
        log.info(String.format(STRING_LOG_FORMAT, SERVICE_NAME, methodName, INICIO), categoria);

        var produtoEntityList = produtoRepository.findByCategoria(categoria.name());

        var produtoList = produtoEntityMapper.toListProduto(produtoEntityList);

        log.info(String.format(STRING_LOG_FORMAT, SERVICE_NAME, methodName, FIM), categoria);

        return produtoList;
    }

    @Override
    public Optional<Produto> buscarPorId(String id) {
        var methodName = "buscarPorId";
        log.info(String.format(STRING_LOG_FORMAT, SERVICE_NAME, methodName, INICIO), id);

        var produtoEntity = produtoRepository.findById(id);

        if (produtoEntity.isEmpty()) {
            log.info(String.format(STRING_LOG_FORMAT, SERVICE_NAME, methodName, FIM),
                    String.format("Produto com id %s não encontrado", id));
            return Optional.empty();
        }

        var produto = produtoEntityMapper.toProduto(produtoEntity.get());

        log.info(String.format(STRING_LOG_FORMAT, SERVICE_NAME, methodName, FIM), produto);

        return Optional.of(produto);
    }
}