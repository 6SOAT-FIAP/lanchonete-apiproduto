package pos.fiap.lanchonete.apiproduto.adapter.out.mysql;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import pos.fiap.lanchonete.apiproduto.domain.enums.CategoriaEnum;
import pos.fiap.lanchonete.apiproduto.adapter.out.mysql.entities.ProdutoEntity;
import pos.fiap.lanchonete.apiproduto.adapter.out.mysql.entities.mapper.ProdutoEntityMapper;
import pos.fiap.lanchonete.apiproduto.adapter.out.mysql.repository.ProdutoRepository;
import pos.fiap.lanchonete.apiproduto.domain.model.entity.Produto;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProdutoMySQLAdapterTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private ProdutoEntityMapper produtoEntityMapper;

    @InjectMocks
    private ProdutoMySQLAdapter produtoMySQLAdapter;

    private Produto produto;
    private ProdutoEntity produtoEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        produto = Produto.builder().id("1").nome("Produto").categoria(CategoriaEnum.BEBIDA).preco(15.0).descricao("Descrição").build();
        produtoEntity = new ProdutoEntity(1L, "Produto", CategoriaEnum.BEBIDA, 10.0, "Descrição", null);
    }

    @Test
    void testCadastrarProduto() {
        when(produtoEntityMapper.toEntity(null, produto)).thenReturn(produtoEntity);
        when(produtoRepository.save(produtoEntity)).thenReturn(produtoEntity);
        when(produtoEntityMapper.toProduto(produtoEntity)).thenReturn(produto);

        Produto resultado = produtoMySQLAdapter.cadastrarProduto(produto);

        assertNotNull(resultado);
        assertEquals(produto.getNome(), resultado.getNome());
        verify(produtoRepository).save(produtoEntity);
    }

    @Test
    void testAlterarProduto() {
        when(produtoEntityMapper.toEntity(1L, produto)).thenReturn(produtoEntity);
        when(produtoRepository.save(produtoEntity)).thenReturn(produtoEntity);
        when(produtoEntityMapper.toProduto(produtoEntity)).thenReturn(produto);

        Produto resultado = produtoMySQLAdapter.alterarProduto(1L, produto);

        assertNotNull(resultado);
        assertEquals(produto.getNome(), resultado.getNome());
        verify(produtoRepository).save(produtoEntity);
    }

    @Test
    void testRemoverProduto() {
        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produtoEntity));

        produtoMySQLAdapter.removerProduto(1L);

        verify(produtoRepository).delete(produtoEntity);
    }

    @Test
    void testBuscarProdutoPorCategoria() {
        ProdutoEntity produtoEntity1 = new ProdutoEntity(1L, "Produto", CategoriaEnum.BEBIDA, 10.0, "Descrição", null);
        ProdutoEntity produtoEntity2 = new ProdutoEntity(2L, "Produto", CategoriaEnum.BEBIDA, 12.0, "Descrição", null);
        when(produtoRepository.findByCategoria(CategoriaEnum.BEBIDA)).thenReturn(List.of(produtoEntity1, produtoEntity2));
        when(produtoEntityMapper.toListProduto(List.of(produtoEntity1, produtoEntity2))).thenReturn(List.of(produto, produto));

        List<Produto> resultado = produtoMySQLAdapter.buscarProdutoPorCategoria(CategoriaEnum.BEBIDA);

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Produto", resultado.get(0).getNome());
        assertEquals("Produto", resultado.get(1).getNome());
    }

    @Test
    void testBuscarPorIdProdutoExistente() {
        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produtoEntity));
        when(produtoEntityMapper.toProduto(produtoEntity)).thenReturn(produto);

        Optional<Produto> resultado = produtoMySQLAdapter.buscarPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals(produto.getNome(), resultado.get().getNome());
    }

    @Test
    void testBuscarPorIdProdutoNaoExistente() {
        when(produtoRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Produto> resultado = produtoMySQLAdapter.buscarPorId(1L);

        assertFalse(resultado.isPresent());
    }

    @Test
    void testBuscarPorIds() {
        List<Long> ids = List.of(1L, 2L, 3L);
        ProdutoEntity produtoEntity1 = new ProdutoEntity(1L, "Produto 1", CategoriaEnum.BEBIDA, 10.0, "Descrição 1", null);
        ProdutoEntity produtoEntity2 = new ProdutoEntity(2L, "Produto 2", CategoriaEnum.LANCHE, 12.0, "Descrição 2", null);
        ProdutoEntity produtoEntity3 = new ProdutoEntity(3L, "Produto 3", CategoriaEnum.BEBIDA, 15.0, "Descrição 3", null);

        when(produtoRepository.findAllByIdIn(ids)).thenReturn(List.of(produtoEntity1, produtoEntity2, produtoEntity3));
        when(produtoEntityMapper.toListProduto(List.of(produtoEntity1, produtoEntity2, produtoEntity3)))
                .thenReturn(List.of(produto, produto, produto));

        List<Produto> resultado = produtoMySQLAdapter.buscarPorIds(ids);

        assertNotNull(resultado);
        assertEquals(3, resultado.size());
        assertEquals("Produto", resultado.get(0).getNome());
        assertEquals("Produto", resultado.get(1).getNome());
        assertEquals("Produto", resultado.get(2).getNome());
        verify(produtoRepository).findAllByIdIn(ids);
        verify(produtoEntityMapper).toListProduto(List.of(produtoEntity1, produtoEntity2, produtoEntity3));
    }
}