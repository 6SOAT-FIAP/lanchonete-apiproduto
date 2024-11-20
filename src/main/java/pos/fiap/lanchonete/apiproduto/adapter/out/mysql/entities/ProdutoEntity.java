package pos.fiap.lanchonete.apiproduto.adapter.out.mysql.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pos.fiap.lanchonete.apiproduto.domain.enums.CategoriaEnum;

import java.net.URL;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_produto")
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private CategoriaEnum categoria;

    private Double preco;

    private String descricao;

    @Column(length = 256)
    private URL imagem;

}