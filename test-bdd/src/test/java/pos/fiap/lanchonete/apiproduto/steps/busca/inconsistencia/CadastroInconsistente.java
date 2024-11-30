package pos.fiap.lanchonete.apiproduto.steps.busca.inconsistencia;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CadastroInconsistente {

    private String payload;
    private HttpResponse<String> response;

    @Given("o seguinte produto da categoria sobremesa foi cadastrado para busca inconsistente:")
    public void o_seguinte_payload_para_cadastrar_um_produto(String body) throws IOException, InterruptedException {
        payload = body;

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8081/api/v1/produto"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(payload))
                .build();

        client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    @When("envio uma requisição GET para {string} para buscar as sobremesas cadastradas com incosistencia")
    public void envio_uma_requisicao_post_para(String endpoint) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8081" + endpoint))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        response = client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    @Then("o código de resposta deve ser {int} para consulta da categoria inconsistente")
    public void o_codigo_de_resposta_deve_ser(int statusCode) {
        assertNotNull(response);
        assertEquals(statusCode, response.statusCode());
    }
}