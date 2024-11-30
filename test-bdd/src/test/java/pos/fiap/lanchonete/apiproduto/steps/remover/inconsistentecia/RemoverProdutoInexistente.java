package pos.fiap.lanchonete.apiproduto.steps.remover.inconsistentecia;

import io.cucumber.java.en.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.Assert.*;

public class RemoverProdutoInexistente {

    private HttpResponse<String> response;

    @Given("que um produto com o ID 999 não existe no sistema")
    public void que_um_produto_com_o_id_nao_existe_no_sistema() {

    }

    @When("envio uma requisição DELETE para o produto {string}")
    public void envio_uma_requisicao_delete_para(String produtoId) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8081/api/v1/produto/" + produtoId))
                .header("Content-Type", "application/json")
                .DELETE()
                .build();

        response = client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    @Then("o código de resposta deve ser {int} para produto não encontrado")
    public void o_codigo_de_resposta_deve_ser_para_produto_nao_encontrado(int statusCode) {
        assertNotNull(response);
        assertEquals(statusCode, response.statusCode());
    }
}