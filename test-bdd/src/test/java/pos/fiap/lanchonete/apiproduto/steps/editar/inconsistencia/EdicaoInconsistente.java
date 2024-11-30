package pos.fiap.lanchonete.apiproduto.steps.editar.inconsistencia;

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

public class EdicaoInconsistente {

    private HttpResponse<String> response;

    @Given("o seguinte produto foi cadastrado para ser editado com inconsistencia:")
    public void o_seguinte_produto_foi_cadastrado(String body) throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8081/api/v1/produto"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    @When("envio uma requisição PUT para {string} com o número do produto inconsistente:")
    public void envio_uma_requisicao_put_para_com_os_dados_atualizados(String endpoint, String updatedBody) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8081" + endpoint))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(updatedBody))
                .build();

        response = client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    @Then("o código de resposta deve ser {int} para edição do produto inconsistente")
    public void o_codigo_de_resposta_deve_ser_para_edicao_do_produto(int statusCode) {
        assertNotNull(response);
        assertEquals(statusCode, response.statusCode());
    }
}
