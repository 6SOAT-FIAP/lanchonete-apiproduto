package pos.fiap.lanchonete.apiproduto.adapter.in.api.dto;

import org.junit.jupiter.api.Test;
import pos.fiap.lanchonete.apiproduto.domain.enums.CategoriaEnum;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProdutoResponseDtoTest {

    @Test
    void testProdutoResponseDto() throws MalformedURLException {
        String id = "123";
        String nome = "Produto teste";
        CategoriaEnum categoria = CategoriaEnum.LANCHE;
        Double preco = 12.5;
        String descricao = "Descrição do produto";
        URL imagem = new URL("http://example.com/imagem.png");

        ProdutoResponseDto produtoResponseDto = ProdutoResponseDto.builder()
                .id(id)
                .nome(nome)
                .categoria(categoria)
                .preco(preco)
                .descricao(descricao)
                .imagem(imagem)
                .build();

        assertEquals(id, produtoResponseDto.getId());
        assertEquals(nome, produtoResponseDto.getNome());
        assertEquals(categoria, produtoResponseDto.getCategoria());
        assertEquals(preco, produtoResponseDto.getPreco());
        assertEquals(descricao, produtoResponseDto.getDescricao());
        assertEquals(imagem, produtoResponseDto.getImagem());
    }
}