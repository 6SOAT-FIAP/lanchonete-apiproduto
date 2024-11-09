package pos.fiap.lanchonete.apiproduto.domain.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pos.fiap.lanchonete.apiproduto.adapter.out.mysql.ProdutoMySQLAdapter;
import pos.fiap.lanchonete.apiproduto.domain.model.DadosProduto;
import pos.fiap.lanchonete.apiproduto.domain.model.entity.Produto;
import pos.fiap.lanchonete.apiproduto.domain.enums.CategoriaEnum;
import pos.fiap.lanchonete.apiproduto.domain.model.entity.mapper.ProdutoMapper;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ProdutoUseCaseTest {

    @Mock
    private ProdutoMapper produtoMapper;

    @Mock
    private ProdutoMySQLAdapter produtoMySQLAdapter;

    @InjectMocks
    private ProdutoUseCase produtoUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCadastrarProduto() {
        DadosProduto dadosProduto = DadosProduto.builder()
                .id("1")
                .nome("Produto Teste")
                .categoria(CategoriaEnum.BEBIDA)
                .preco(10.0)
                .descricao("Descrição Teste")
                .build();

        Produto produto = Produto.builder()
                .id("1")
                .nome("Produto Teste")
                .categoria(CategoriaEnum.BEBIDA)
                .preco(10.0)
                .descricao("Descrição Teste")
                .build();

        when(produtoMapper.fromDadosProduto(dadosProduto)).thenReturn(produto);
        when(produtoMySQLAdapter.cadastrarProduto(produto)).thenReturn(produto);
        when(produtoMapper.toDadosProduto(produto)).thenReturn(dadosProduto);

        DadosProduto result = produtoUseCase.cadastrar(dadosProduto);

        assertNotNull(result);
        assertEquals(dadosProduto.getId(), result.getId());
        assertEquals(dadosProduto.getNome(), result.getNome());
        assertEquals(dadosProduto.getPreco(), result.getPreco());
        verify(produtoMapper).fromDadosProduto(dadosProduto);
        verify(produtoMySQLAdapter).cadastrarProduto(produto);
        verify(produtoMapper).toDadosProduto(produto);
    }

    @Test
    void testAlterarProduto() {
        DadosProduto dadosProduto = DadosProduto.builder()
                .id("1")
                .nome("Produto Alterado")
                .categoria(CategoriaEnum.LANCHE)
                .preco(20.0)
                .descricao("Descrição Alterada")
                .build();

        Produto produto = Produto.builder()
                .id("1")
                .nome("Produto Alterado")
                .categoria(CategoriaEnum.LANCHE)
                .preco(20.0)
                .descricao("Descrição Alterada")
                .build();

        when(produtoMapper.fromDadosProduto(dadosProduto)).thenReturn(produto);
        when(produtoMySQLAdapter.alterarProduto(1L, produto)).thenReturn(produto);
        when(produtoMapper.toDadosProduto(produto)).thenReturn(dadosProduto);

        DadosProduto result = produtoUseCase.alterar(1L, dadosProduto);

        assertNotNull(result);
        assertEquals(dadosProduto.getId(), result.getId());
        assertEquals(dadosProduto.getNome(), result.getNome());
        verify(produtoMapper).fromDadosProduto(dadosProduto);
        verify(produtoMySQLAdapter).alterarProduto(1L, produto);
        verify(produtoMapper).toDadosProduto(produto);
    }

    @Test
    void testRemoverProduto() {
        Produto produto = Produto.builder()
                .id("1")
                .nome("Produto Removido")
                .categoria(CategoriaEnum.BEBIDA)
                .preco(15.0)
                .descricao("Descrição Removida")
                .build();

        when(produtoMySQLAdapter.buscarPorId(1L)).thenReturn(Optional.of(produto));

        Optional<Produto> result = produtoUseCase.remover(1L);

        assertTrue(result.isPresent());
        assertEquals(produto.getId(), result.get().getId());
        verify(produtoMySQLAdapter).buscarPorId(1L);
        verify(produtoMySQLAdapter).removerProduto(1L);
    }

    @Test
    void testRemoverProdutoNotFound() {
        when(produtoMySQLAdapter.buscarPorId(1L)).thenReturn(Optional.empty());

        Optional<Produto> result = produtoUseCase.remover(1L);

        assertTrue(result.isEmpty());
        verify(produtoMySQLAdapter).buscarPorId(1L);
        verify(produtoMySQLAdapter, never()).removerProduto(1L);
    }

    @Test
    void testBuscarPorCategoria() {
        DadosProduto dadosProduto = DadosProduto.builder()
                .id("1")
                .nome("Produto Teste")
                .categoria(CategoriaEnum.BEBIDA)
                .preco(10.0)
                .descricao("Descrição Teste")
                .build();

        Produto produto = Produto.builder()
                .id("1")
                .nome("Produto Teste")
                .categoria(CategoriaEnum.BEBIDA)
                .preco(10.0)
                .descricao("Descrição Teste")
                .build();

        when(produtoMySQLAdapter.buscarProdutoPorCategoria(CategoriaEnum.BEBIDA)).thenReturn(List.of(produto));
        when(produtoMapper.toListDadosProduto(List.of(produto))).thenReturn(List.of(dadosProduto));

        List<DadosProduto> result = produtoUseCase.buscarPorCategoria(CategoriaEnum.BEBIDA);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(dadosProduto.getId(), result.get(0).getId());
        verify(produtoMySQLAdapter).buscarProdutoPorCategoria(CategoriaEnum.BEBIDA);
        verify(produtoMapper).toListDadosProduto(List.of(produto));
    }
}