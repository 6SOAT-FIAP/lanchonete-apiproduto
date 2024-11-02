package pos.fiap.lanchonete.apiproduto.adapter.out.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pos.fiap.lanchonete.apiproduto.adapter.out.mongo.entities.ProdutoEntity;

import java.util.List;

@Repository
public interface ProdutoRepository extends MongoRepository<ProdutoEntity, String> {
    List<ProdutoEntity> findByCategoria(String categoria);
}
