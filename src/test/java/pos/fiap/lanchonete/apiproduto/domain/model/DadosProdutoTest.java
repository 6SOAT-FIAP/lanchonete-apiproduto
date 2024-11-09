package pos.fiap.lanchonete.apiproduto.domain.model;

import org.junit.jupiter.api.Test;
import pos.fiap.lanchonete.apiproduto.domain.enums.CategoriaEnum;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

class DadosProdutoTest {

    @Test
    void testDadosProdutoCreation() throws Exception {
        DadosProduto dadosProduto = DadosProduto.builder()
                .id("1")
                .nome("Produto Teste")
                .categoria(CategoriaEnum.BEBIDA)
                .preco(15.0)
                .descricao("Descrição Teste")
                .imagem(new URL("http://exemplo.com/imagem.jpg"))
                .build();

        assertNotNull(dadosProduto);
        assertEquals("1", dadosProduto.getId());
        assertEquals("Produto Teste", dadosProduto.getNome());
        assertEquals(CategoriaEnum.BEBIDA, dadosProduto.getCategoria());
        assertEquals(15.0, dadosProduto.getPreco());
        assertEquals("Descrição Teste", dadosProduto.getDescricao());
        assertEquals("http://exemplo.com/imagem.jpg", dadosProduto.getImagem().toString());
    }
}