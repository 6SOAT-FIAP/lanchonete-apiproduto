package pos.fiap.lanchonete.apiproduto.adapter.in.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pos.fiap.lanchonete.apiproduto.adapter.in.api.dto.ProdutoRequestDto;
import pos.fiap.lanchonete.apiproduto.adapter.in.api.dto.ProdutoResponseDto;
import pos.fiap.lanchonete.apiproduto.adapter.in.api.mapper.ProdutoDtoMapper;
import pos.fiap.lanchonete.apiproduto.port.ProdutoUseCasePort;

import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static pos.fiap.lanchonete.apiproduto.domain.enums.CategoriaEnum.getCategoriaByLabel;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/produto")
@RestController
public class ProdutoController {

    private final ProdutoDtoMapper dtoMapper;
    private final ProdutoUseCasePort produtoUseCasePort;

    @ResponseStatus(CREATED)
    @PostMapping
    public ResponseEntity<ProdutoResponseDto> cadastrar(@RequestBody ProdutoRequestDto produtoRequest) {
        log.info("Produto request: {}", produtoRequest);

        var dadosProduto = dtoMapper.toDadosProdutoFromRequestDto(produtoRequest);

        var produtoCadastrado = produtoUseCasePort.cadastrar(dadosProduto);

        var produtoResponse = dtoMapper.toProdutoResponseDtoFromDadosProduto(produtoCadastrado);
        log.info("Produto response: {}", produtoResponse);

        return ResponseEntity.status(HttpStatus.CREATED).body(produtoResponse);
    }

    @ResponseStatus(OK)
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDto> editar(@PathVariable Long id, @RequestBody ProdutoRequestDto produtoRequestDto) {
        log.info("Produto request: {}", produtoRequestDto);

        var dadosProduto = dtoMapper.toDadosProdutoFromRequestDto(produtoRequestDto);

        var produtoAlterado = produtoUseCasePort.alterar(id, dadosProduto);

        var produtoResponse = dtoMapper.toProdutoResponseDtoFromDadosProduto(produtoAlterado);

        log.info("Produto response: {}", produtoResponse);
        return ResponseEntity.status(OK).body(produtoResponse);
    }

    @ResponseStatus(OK)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> remover(@PathVariable Long id) {
        log.info("Produto id: {}", id);

        var produto = produtoUseCasePort.remover(id);

        if (produto.isEmpty()) {
            return ResponseEntity.status(NOT_FOUND).build();
        }

        log.info("Produto Id: {} removido", id);
        return ResponseEntity.status(OK).body("Produto removido com sucesso");
    }

    @ResponseStatus(OK)
    @GetMapping("/{categoria}")
    public ResponseEntity<List<ProdutoResponseDto>> buscar(@PathVariable String categoria) {
        log.info("Categoria request: {}", categoria);

        var categoriaEnum = getCategoriaByLabel(categoria);

        if (categoriaEnum.isEmpty()) {
            log.error("Categoria informada não existe: {}", categoria);
            return ResponseEntity.status(NOT_FOUND).build();
        }

        var produtosList = produtoUseCasePort.buscarPorCategoria(categoriaEnum.get());

        var produtoResponseList = dtoMapper.toListProdutoResponseDtoFromListDadosProduto(produtosList);

        log.info("List Produto response: {} por categoria: {}", produtoResponseList, categoria);
        return ResponseEntity.status(OK).body(produtoResponseList);
    }
}