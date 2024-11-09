package pos.fiap.lanchonete.apiproduto.domain.model.entity;

import org.junit.jupiter.api.Test;
import pos.fiap.lanchonete.apiproduto.domain.enums.CategoriaEnum;

import java.net.URL;
import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoTest {

    @Test
    void testProdutoBuilder() throws MalformedURLException {
        URL url = new URL("https://www.example.com");

        Produto produto = Produto.builder()
                .id("1")
                .nome("Produto Teste")
                .categoria(CategoriaEnum.BEBIDA)
                .preco(15.0)
                .descricao("Descrição Teste")
                .imagem(url)
                .build();

        assertNotNull(produto);
        assertEquals("1", produto.getId());
        assertEquals("Produto Teste", produto.getNome());
        assertEquals(CategoriaEnum.BEBIDA, produto.getCategoria());
        assertEquals(15.0, produto.getPreco());
        assertEquals("Descrição Teste", produto.getDescricao());
        assertEquals(url, produto.getImagem());
    }
}