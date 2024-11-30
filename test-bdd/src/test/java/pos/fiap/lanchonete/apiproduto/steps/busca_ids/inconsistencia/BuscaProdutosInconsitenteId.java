package pos.fiap.lanchonete.apiproduto.steps.busca_ids.inconsistencia;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.Assert.*;

public class BuscaProdutosInconsitenteId {

    private HttpResponse<String> response;
    private String produtoId;

    @Given("o seguinte ID {string} de um produto inexistente")
    public void o_seguinte_id_de_um_produto_inexistente(String idInexistente) {
        this.produtoId = idInexistente;
    }

    @When("envio uma requisição GET para {string} para buscar um produto inexistente")
    public void envio_uma_requisicao_get_para_buscar_um_produto_inexistente(String endpoint) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8081" + endpoint))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        response = client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    @Then("o código de resposta deve ser {int} para a busca por ID inexistente")
    public void o_codigo_de_resposta_deve_ser_para_a_busca_por_id_inexistente(int statusCode) {
        assertNotNull(response);
        assertEquals(statusCode, response.statusCode());
    }
}