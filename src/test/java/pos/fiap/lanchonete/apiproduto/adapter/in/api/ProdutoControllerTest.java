package pos.fiap.lanchonete.apiproduto.adapter.in.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pos.fiap.lanchonete.apiproduto.adapter.in.api.dto.ProdutoRequestDto;
import pos.fiap.lanchonete.apiproduto.adapter.in.api.dto.ProdutoResponseDto;
import pos.fiap.lanchonete.apiproduto.adapter.in.api.mapper.ProdutoDtoMapper;
import pos.fiap.lanchonete.apiproduto.domain.enums.CategoriaEnum;
import pos.fiap.lanchonete.apiproduto.domain.model.DadosProduto;
import pos.fiap.lanchonete.apiproduto.domain.model.entity.Produto;
import pos.fiap.lanchonete.apiproduto.port.ProdutoUseCasePort;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProdutoControllerTest {

    @Mock
    private ProdutoDtoMapper dtoMapper;

    @Mock
    private ProdutoUseCasePort produtoUseCasePort;

    @InjectMocks
    private ProdutoController produtoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCadastrarProduto() {
        ProdutoRequestDto requestDto = ProdutoRequestDto.builder().build();
        DadosProduto dadosProduto = DadosProduto.builder().build();
        ProdutoResponseDto responseDto = ProdutoResponseDto.builder().build();

        when(dtoMapper.toDadosProdutoFromRequestDto(Mockito.any(ProdutoRequestDto.class))).thenReturn(dadosProduto);
        when(produtoUseCasePort.cadastrar(Mockito.any(DadosProduto.class))).thenReturn(dadosProduto);
        when(dtoMapper.toProdutoResponseDtoFromDadosProduto(Mockito.any(DadosProduto.class))).thenReturn(responseDto);

        ResponseEntity<ProdutoResponseDto> response = produtoController.cadastrar(requestDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(responseDto, response.getBody());
        verify(dtoMapper).toDadosProdutoFromRequestDto(requestDto);
        verify(produtoUseCasePort).cadastrar(any());
        verify(dtoMapper).toProdutoResponseDtoFromDadosProduto(any());
    }

    @Test
    void testEditarProduto() {
        Long id = 1L;
        ProdutoRequestDto requestDto = ProdutoRequestDto.builder().build();
        DadosProduto dadosProduto = DadosProduto.builder().build();
        ProdutoResponseDto responseDto = ProdutoResponseDto.builder().build();

        when(dtoMapper.toDadosProdutoFromRequestDto(requestDto)).thenReturn(dadosProduto);
        when(produtoUseCasePort.alterar(id, dadosProduto)).thenReturn(dadosProduto);
        when(dtoMapper.toProdutoResponseDtoFromDadosProduto(dadosProduto)).thenReturn(responseDto);

        ResponseEntity<ProdutoResponseDto> response = produtoController.editar(id, requestDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDto, response.getBody());
        verify(dtoMapper).toDadosProdutoFromRequestDto(requestDto);
        verify(produtoUseCasePort).alterar(id, dadosProduto);
        verify(dtoMapper).toProdutoResponseDtoFromDadosProduto(dadosProduto);
    }

    @Test
    void testRemoverProdutoSucesso() {
        Long id = 1L;
        Produto produto = Produto.builder().build();

        when(produtoUseCasePort.remover(id)).thenReturn(Optional.of(produto));

        ResponseEntity<String> response = produtoController.remover(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Produto removido com sucesso", response.getBody());
        verify(produtoUseCasePort).remover(id);
    }

    @Test
    void testRemoverProdutoNotFound() {
        Long id = 1L;

        when(produtoUseCasePort.remover(id)).thenReturn(Optional.empty());

        ResponseEntity<String> response = produtoController.remover(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(produtoUseCasePort).remover(id);
    }

    @Test
    void testBuscarProdutosPorCategoria() {
        String categoria = "Lanche";
        CategoriaEnum categoriaEnum = CategoriaEnum.LANCHE;
        DadosProduto dadosProduto = DadosProduto.builder().build();
        ProdutoResponseDto produtoResponseDto = ProdutoResponseDto.builder().build();

        when(produtoUseCasePort.buscarPorCategoria(categoriaEnum)).thenReturn(List.of(dadosProduto));

        when(dtoMapper.toListProdutoResponseDtoFromListDadosProduto(anyList())).thenReturn(List.of(produtoResponseDto));

        ResponseEntity<List<ProdutoResponseDto>> response = produtoController.buscar(categoria);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isEmpty());
        verify(produtoUseCasePort).buscarPorCategoria(categoriaEnum);
        verify(dtoMapper).toListProdutoResponseDtoFromListDadosProduto(anyList());
    }

    @Test
    void testBuscarProdutosPorCategoriaNotFound() {
        String categoria = "Sandubas";

        ResponseEntity<List<ProdutoResponseDto>> response = produtoController.buscar(categoria);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}