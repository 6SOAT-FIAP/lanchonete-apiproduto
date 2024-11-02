package pos.fiap.lanchonete.apiproduto.adapter.out.mongo.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import pos.fiap.lanchonete.apiproduto.domain.enums.CategoriaEnum;

import java.net.URL;

@Data
@Builder
@Document("produto")
public class ProdutoEntity {

    @Id
    private String id;
    private String nome;
    private CategoriaEnum categoria;
    private Double preco;
    private String descricao;
    private URL imagem;
}
