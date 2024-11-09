package pos.fiap.lanchonete.apiproduto.adapter.in.api.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pos.fiap.lanchonete.apiproduto.adapter.in.api.dto.ProdutoRequestDto;
import pos.fiap.lanchonete.apiproduto.adapter.in.api.dto.ProdutoResponseDto;
import pos.fiap.lanchonete.apiproduto.domain.enums.CategoriaEnum;
import pos.fiap.lanchonete.apiproduto.domain.model.DadosProduto;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoDtoMapperTest {

    private ProdutoDtoMapper produtoDtoMapper;

    @BeforeEach
    void setUp() {
        produtoDtoMapper = new ProdutoDtoMapperImpl();
    }

    @Test
    void testToDadosProdutoFromRequestDto() throws MalformedURLException {
        DadosProduto resultado = produtoDtoMapper.toDadosProdutoFromRequestDto(getProdutoRequestDto());

        assertNotNull(resultado);
        assertEquals("123", resultado.getId());
        assertEquals("Produto teste", resultado.getNome());
        assertEquals(CategoriaEnum.LANCHE, resultado.getCategoria());
        assertEquals(12.5, resultado.getPreco());
        assertEquals("Descrição do produto", resultado.getDescricao());
        assertEquals(new URL("http://example.com/imagem.png"), resultado.getImagem());
    }

    @Test
    void testToProdutoResponseDtoFromDadosProduto() throws MalformedURLException {
        ProdutoResponseDto resultado = produtoDtoMapper.toProdutoResponseDtoFromDadosProduto(getDadosProduto());

        assertNotNull(resultado);
        assertEquals("123", resultado.getId());
        assertEquals("Produto teste", resultado.getNome());
        assertEquals(CategoriaEnum.LANCHE, resultado.getCategoria());
        assertEquals(12.5, resultado.getPreco());
        assertEquals("Descrição do produto", resultado.getDescricao());
        assertEquals(new URL("http://example.com/imagem.png"), resultado.getImagem());
    }

    @Test
    void testToListProdutoResponseDtoFromListDadosProduto() throws MalformedURLException {
        List<ProdutoResponseDto> resultado = produtoDtoMapper.toListProdutoResponseDtoFromListDadosProduto(List.of(getDadosProduto()));

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("123", resultado.get(0).getId());
        assertEquals("Produto teste", resultado.get(0).getNome());
        assertEquals(CategoriaEnum.LANCHE, resultado.get(0).getCategoria());
        assertEquals(12.5, resultado.get(0).getPreco());
        assertEquals("Descrição do produto", resultado.get(0).getDescricao());
        assertEquals(new URL("http://example.com/imagem.png"), resultado.get(0).getImagem());
    }

    private ProdutoRequestDto getProdutoRequestDto() throws MalformedURLException {
        return ProdutoRequestDto.builder()
                .id("123")
                .nome("Produto teste")
                .categoria(CategoriaEnum.LANCHE)
                .preco(12.5)
                .descricao("Descrição do produto")
                .imagem(new URL("http://example.com/imagem.png"))
                .build();
    }

    private ProdutoResponseDto getProdutoResponseDto() throws MalformedURLException {
        return ProdutoResponseDto.builder()
                .id("123")
                .nome("Produto teste")
                .categoria(CategoriaEnum.LANCHE)
                .preco(12.5)
                .descricao("Descrição do produto")
                .imagem(new URL("http://example.com/imagem.png"))
                .build();
    }

    private DadosProduto getDadosProduto() throws MalformedURLException {
        return DadosProduto.builder()
                .id("123")
                .nome("Produto teste")
                .categoria(CategoriaEnum.LANCHE)
                .preco(12.5)
                .descricao("Descrição do produto")
                .imagem(new URL("http://example.com/imagem.png"))
                .build();
    }
}