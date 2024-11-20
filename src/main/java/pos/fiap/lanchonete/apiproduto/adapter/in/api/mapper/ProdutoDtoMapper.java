package pos.fiap.lanchonete.apiproduto.adapter.in.api.mapper;

import org.mapstruct.Mapper;
import pos.fiap.lanchonete.apiproduto.adapter.in.api.dto.ProdutoRequestDto;
import pos.fiap.lanchonete.apiproduto.adapter.in.api.dto.ProdutoResponseDto;
import pos.fiap.lanchonete.apiproduto.domain.model.DadosProduto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProdutoDtoMapper {

    DadosProduto toDadosProdutoFromRequestDto(ProdutoRequestDto requestDto);

    ProdutoResponseDto toProdutoResponseDtoFromDadosProduto(DadosProduto dadosProduto);

    List<ProdutoResponseDto> toListProdutoResponseDtoFromListDadosProduto(List<DadosProduto> dadosProduto);

}
