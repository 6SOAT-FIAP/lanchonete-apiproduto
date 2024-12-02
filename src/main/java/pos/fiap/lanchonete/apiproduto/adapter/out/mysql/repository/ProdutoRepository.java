package pos.fiap.lanchonete.apiproduto.adapter.out.mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pos.fiap.lanchonete.apiproduto.adapter.out.mysql.entities.ProdutoEntity;
import pos.fiap.lanchonete.apiproduto.domain.enums.CategoriaEnum;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {
    List<ProdutoEntity> findByCategoria(CategoriaEnum categoria);

    List<ProdutoEntity> findAllByIdIn(List<Long> ids);
}