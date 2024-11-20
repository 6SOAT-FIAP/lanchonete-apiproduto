package pos.fiap.lanchonete.apiproduto.adapter.out.mysql.entities.mappers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import pos.fiap.lanchonete.apiproduto.adapter.out.mysql.entities.ProdutoEntity;
import pos.fiap.lanchonete.apiproduto.adapter.out.mysql.entities.mapper.ProdutoEntityMapper;
import pos.fiap.lanchonete.apiproduto.domain.model.entity.Produto;
import pos.fiap.lanchonete.apiproduto.domain.enums.CategoriaEnum;

import java.util.List;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProdutoEntityMapperTest {

    private ProdutoEntityMapper produtoEntityMapper;

    @BeforeEach
    void setUp() {
        produtoEntityMapper = Mappers.getMapper(ProdutoEntityMapper.class);
    }

    @Test
    void testToEntity() throws MalformedURLException {
        Produto produto = Produto.builder().build();
        produto.setId("1");
        produto.setNome("Produto Teste");
        produto.setCategoria(CategoriaEnum.BEBIDA);
        produto.setPreco(10.99);
        produto.setDescricao("Descrição do Produto");
        produto.setImagem(new URL("http://example.com/imagem.png"));

        ProdutoEntity produtoEntity = produtoEntityMapper.toEntity(1L, produto);

        assertEquals(1L, produtoEntity.getId());
        assertEquals("Produto Teste", produtoEntity.getNome());
        assertEquals(CategoriaEnum.BEBIDA, produtoEntity.getCategoria());
        assertEquals(10.99, produtoEntity.getPreco());
        assertEquals("Descrição do Produto", produtoEntity.getDescricao());
        assertEquals(new URL("http://example.com/imagem.png"), produtoEntity.getImagem());
    }

    @Test
    void testToProduto() throws MalformedURLException {
        ProdutoEntity produtoEntity = new ProdutoEntity(1L, "Produto Teste", CategoriaEnum.BEBIDA, 10.99, "Descrição do Produto", new URL("http://example.com/imagem.png"));

        Produto produto = produtoEntityMapper.toProduto(produtoEntity);

        assertEquals("1", produto.getId());
        assertEquals("Produto Teste", produto.getNome());
        assertEquals(CategoriaEnum.BEBIDA, produto.getCategoria());
        assertEquals(10.99, produto.getPreco());
        assertEquals("Descrição do Produto", produto.getDescricao());
        assertEquals(new URL("http://example.com/imagem.png"), produto.getImagem());
    }

    @Test
    void testToListProduto() {
        ProdutoEntity produtoEntity1 = new ProdutoEntity(1L, "Produto 1", CategoriaEnum.BEBIDA, 10.99, "Descrição 1", null);
        ProdutoEntity produtoEntity2 = new ProdutoEntity(2L, "Produto 2", CategoriaEnum.LANCHE, 15.50, "Descrição 2", null);

        List<ProdutoEntity> produtoEntityList = List.of(produtoEntity1, produtoEntity2);

        List<Produto> produtoList = produtoEntityMapper.toListProduto(produtoEntityList);

        assertEquals(2, produtoList.size());
        assertEquals("1", produtoList.get(0).getId());
        assertEquals("Produto 2", produtoList.get(1).getNome());
    }
}
