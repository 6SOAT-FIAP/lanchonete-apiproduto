package pos.fiap.lanchonete.apiproduto.steps.busca_categoria.sucesso;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.Assert.*;

public class BuscaAcompanhamentos {

    private String payload;
    private HttpResponse<String> response;

    @Given("o seguinte produto da categoria acompanhamento foi cadastrado:")
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

    @When("envio uma requisição GET para {string} para buscar os acompanhamento cadastrados")
    public void envio_uma_requisicao_post_para(String endpoint) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8081" + endpoint))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        response = client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    @Then("o código de resposta deve ser {int} para consulta da categoria acompanhamento")
    public void o_codigo_de_resposta_deve_ser(int statusCode) {
        assertNotNull(response);
        assertEquals(statusCode, response.statusCode());
    }

    @And("o corpo da resposta deve conter um produto da categoria ACOMPANHAMENTO")
    public void o_corpo_da_resposta_deve_conter_um_produto_da_categoria() {
        JSONArray actualJsonArray = new JSONArray(response.body());

        boolean categoriaValida = false;

        for (int i = 0; i < actualJsonArray.length(); i++) {

            JSONObject product = actualJsonArray.getJSONObject(i);

            if ("ACOMPANHAMENTO".equals(product.getString("categoria"))) {
                categoriaValida = true;
                break;
            }
        }

        assertTrue(categoriaValida);
    }
}