package pos.fiap.lanchonete.apiproduto.adapter.out.mysql.entities;

import org.junit.jupiter.api.Test;
import pos.fiap.lanchonete.apiproduto.domain.enums.CategoriaEnum;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URL;
import java.net.MalformedURLException;

class ProdutoEntityTest {

    @Test
    void testProdutoEntityCreation() throws MalformedURLException {

        URL imagemUrl = new URL("http://example.com/imagem.png");

        ProdutoEntity produto = ProdutoEntity.builder()
                .nome("Produto teste")
                .categoria(CategoriaEnum.BEBIDA)
                .preco(19.99)
                .descricao("Descrição do produto")
                .imagem(imagemUrl)
                .build();

        assertNotNull(produto);
        assertEquals("Produto teste", produto.getNome());
        assertEquals(CategoriaEnum.BEBIDA, produto.getCategoria());
        assertEquals(19.99, produto.getPreco());
        assertEquals("Descrição do produto", produto.getDescricao());
        assertEquals(imagemUrl, produto.getImagem());
    }
}
