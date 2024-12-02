package pos.fiap.lanchonete.apiproduto.steps.remover.sucesso;

import io.cucumber.java.en.*;
import org.json.JSONObject;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.Assert.*;

public class RemoverProduto {

    private HttpResponse<String> response;
    private String produtoId;

    @Given("que um produto foi criado no sistema")
    public void que_um_produto_foi_criado_no_sistema() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        JSONObject produto = new JSONObject();
        produto.put("nome", "X-bacon");
        produto.put("categoria", "LANCHE");
        produto.put("preco", 10);
        produto.put("descricao", "Pão, carne, queijo e bacon");
        produto.put("imagem", "https://static.itdg.com.br/images/640-440/49687a8a7a7110c7f560b9c7e96a9d0e/254679-shutterstock-364110890-1-.jpg");

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8081/api/v1/produto"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(produto.toString()))
                .build();

        response = client.send(request, HttpResponse.BodyHandlers.ofString());
        assertEquals(201, response.statusCode());

        JSONObject jsonResponse = new JSONObject(response.body());
        produtoId = jsonResponse.getString("id");
        assertNotNull(produtoId);
    }

    @When("envio uma requisição DELETE com o ID do produto criado")
    public void envio_uma_requisicao_delete_para_com_o_id_do_produto_criado() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8081/api/v1/produto/" + produtoId))
                .header("Content-Type", "application/json")
                .DELETE()
                .build();

        response = client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    @Then("o código de resposta deve ser {int} para deleção do produto")
    public void o_codigo_de_resposta_deve_ser_para_delecao_do_produto(int statusCode) {
        assertNotNull(response);
        assertEquals(statusCode, response.statusCode());
    }
}