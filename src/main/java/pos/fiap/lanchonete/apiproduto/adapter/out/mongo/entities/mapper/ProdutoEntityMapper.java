package pos.fiap.lanchonete.apiproduto.adapter.out.mongo.entities.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pos.fiap.lanchonete.apiproduto.adapter.out.mongo.entities.ProdutoEntity;
import pos.fiap.lanchonete.apiproduto.domain.model.entity.Produto;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", imports = UUID.class)

public interface ProdutoEntityMapper {
    @Mapping(source = "id", target = "id", defaultExpression = "java(UUID.randomUUID().toString())")
    ProdutoEntity toEntity(String id, Produto produto);

    Produto toProduto(ProdutoEntity produtoEntity);

    List<Produto> toListProduto(List<ProdutoEntity> produtoEntityList);
}
