package pos.fiap.lanchonete.apiproduto.steps.cadastro.sucesso;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CadastroBebida {

    private String payload;
    private HttpResponse<String> response;

    @Given("o seguinte payload para cadastrar um produto da categoria bebida:")
    public void o_seguinte_payload_para_cadastrar_um_produto(String body) {
        payload = body;
    }

    @When("envio uma requisição POST para {string} com os dados da bebida")
    public void envio_uma_requisicao_post_para(String endpoint) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8081" + endpoint))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(payload))
                .build();

        response = client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    @Then("o código de resposta deve ser {int} para criação da bebida")
    public void o_codigo_de_resposta_deve_ser(int statusCode) {
        assertNotNull(response);
        assertEquals(statusCode, response.statusCode());
    }

    @And("o corpo da resposta da criação da bebida deve ser:")
    public void o_corpo_da_resposta_deve_ser(String expectedBody) {
        JSONObject actualJson = new JSONObject(response.body());
        JSONObject expectedJson = new JSONObject(expectedBody);

        assertNotNull(actualJson.optString("id", null));
        assertEquals(expectedJson.getString("nome"), actualJson.getString("nome"));
        assertEquals(expectedJson.getString("categoria"), actualJson.getString("categoria"));
        assertEquals(expectedJson.getDouble("preco"), actualJson.getDouble("preco"), 0.0001);
        assertEquals(expectedJson.getString("descricao"), actualJson.getString("descricao"));
        assertEquals(expectedJson.getString("imagem"), actualJson.getString("imagem"));
    }
}