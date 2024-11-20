package pos.fiap.lanchonete.apiproduto.adapter.in.api.dto;

import org.junit.jupiter.api.Test;
import pos.fiap.lanchonete.apiproduto.domain.enums.CategoriaEnum;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProdutoRequestDtoTest {

    @Test
    void testProdutoRequestDto() throws MalformedURLException {
        String id = "123";
        String nome = "Produto teste";
        CategoriaEnum categoria = CategoriaEnum.LANCHE;
        Double preco = 12.5;
        String descricao = "Descrição do produto";
        URL imagem = new URL("http://example.com/imagem.png");

        ProdutoRequestDto produtoRequestDto = ProdutoRequestDto.builder()
                .id(id)
                .nome(nome)
                .categoria(categoria)
                .preco(preco)
                .descricao(descricao)
                .imagem(imagem)
                .build();

        assertEquals(id, produtoRequestDto.getId());
        assertEquals(nome, produtoRequestDto.getNome());
        assertEquals(categoria, produtoRequestDto.getCategoria());
        assertEquals(preco, produtoRequestDto.getPreco());
        assertEquals(descricao, produtoRequestDto.getDescricao());
        assertEquals(imagem, produtoRequestDto.getImagem());
    }
}