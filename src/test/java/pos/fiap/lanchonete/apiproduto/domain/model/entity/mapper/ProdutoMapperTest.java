package pos.fiap.lanchonete.apiproduto.domain.model.entity.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import pos.fiap.lanchonete.apiproduto.domain.model.DadosProduto;
import pos.fiap.lanchonete.apiproduto.domain.model.entity.Produto;
import pos.fiap.lanchonete.apiproduto.domain.enums.CategoriaEnum;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoMapperTest {

    private ProdutoMapper produtoMapper;

    @BeforeEach
    void setUp() {
        produtoMapper = Mappers.getMapper(ProdutoMapper.class);
    }

    @Test
    void testFromDadosProduto() {
        DadosProduto dadosProduto = DadosProduto.builder()
                .id("1")
                .nome("Produto Teste")
                .categoria(CategoriaEnum.BEBIDA)
                .preco(20.0)
                .descricao("Descrição Teste")
                .build();

        Produto produto = produtoMapper.fromDadosProduto(dadosProduto);

        assertNotNull(produto);
        assertEquals("1", produto.getId());
        assertEquals("Produto Teste", produto.getNome());
        assertEquals(CategoriaEnum.BEBIDA, produto.getCategoria());
        assertEquals(20.0, produto.getPreco());
        assertEquals("Descrição Teste", produto.getDescricao());
    }

    @Test
    void testToDadosProduto() {
        Produto produto = Produto.builder()
                .id("2")
                .nome("Produto Teste 2")
                .categoria(CategoriaEnum.LANCHE)
                .preco(10.0)
                .descricao("Descrição Teste 2")
                .build();

        DadosProduto dadosProduto = produtoMapper.toDadosProduto(produto);

        assertNotNull(dadosProduto);
        assertEquals("2", dadosProduto.getId());
        assertEquals("Produto Teste 2", dadosProduto.getNome());
        assertEquals(CategoriaEnum.LANCHE, dadosProduto.getCategoria());
        assertEquals(10.0, dadosProduto.getPreco());
        assertEquals("Descrição Teste 2", dadosProduto.getDescricao());
    }

    @Test
    void testToListDadosProduto() {
        Produto produto1 = Produto.builder()
                .id("1")
                .nome("Produto Teste 1")
                .categoria(CategoriaEnum.BEBIDA)
                .preco(15.0)
                .descricao("Descrição Teste 1")
                .build();
        Produto produto2 = Produto.builder()
                .id("2")
                .nome("Produto Teste 2")
                .categoria(CategoriaEnum.LANCHE)
                .preco(10.0)
                .descricao("Descrição Teste 2")
                .build();

        List<Produto> produtoList = List.of(produto1, produto2);

        List<DadosProduto> dadosProdutoList = produtoMapper.toListDadosProduto(produtoList);

        assertNotNull(dadosProdutoList);
        assertEquals(2, dadosProdutoList.size());
        assertEquals("1", dadosProdutoList.get(0).getId());
        assertEquals("Produto Teste 1", dadosProdutoList.get(0).getNome());
        assertEquals("2", dadosProdutoList.get(1).getId());
        assertEquals("Produto Teste 2", dadosProdutoList.get(1).getNome());
    }
}