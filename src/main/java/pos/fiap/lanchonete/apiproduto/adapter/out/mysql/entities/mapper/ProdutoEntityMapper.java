package pos.fiap.lanchonete.apiproduto.adapter.out.mysql.entities.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pos.fiap.lanchonete.apiproduto.adapter.out.mysql.entities.ProdutoEntity;
import pos.fiap.lanchonete.apiproduto.domain.model.entity.Produto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProdutoEntityMapper {

    @Mapping(source = "id", target = "id")
    ProdutoEntity toEntity(Long id, Produto produto);

    Produto toProduto(ProdutoEntity produtoEntity);

    List<Produto> toListProduto(List<ProdutoEntity> produtoEntityList);
}
