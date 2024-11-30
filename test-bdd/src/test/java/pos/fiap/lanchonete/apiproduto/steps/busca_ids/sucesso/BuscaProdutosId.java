package pos.fiap.lanchonete.apiproduto.steps.busca_ids.sucesso;

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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BuscaProdutosId {

    private static final String BASE_URL = "http://localhost:8081/api/v1/produto";
    private HttpResponse<String> response;
    private String produtoId;

    @Given("o seguinte produto foi cadastrado para busca por id:")
    public void o_seguinte_payload_para_cadastrar_um_produto(String body) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        response = client.send(request, HttpResponse.BodyHandlers.ofString());
        assertEquals(201, response.statusCode());

        JSONObject responseBody = new JSONObject(response.body());
        produtoId = responseBody.getString("id");
        assertNotNull(produtoId);
    }

    @When("envio uma requisição GET para buscar o produto cadastrado pelo id")
    public void envio_uma_requisicao_get_para_buscar_o_produto_cadastrado() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/?ids=" + produtoId))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        response = client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    @Then("o código de resposta deve ser {int} para a busca do produto pelo id")
    public void o_codigo_de_resposta_deve_ser(int statusCode) {
        assertNotNull(response);
        assertEquals(statusCode, response.statusCode());
    }

    @And("o corpo da resposta deve conter os dados do produto cadastrado e buscado pelo id")
    public void o_corpo_da_resposta_deve_conter_os_dados_do_produto_cadastrado() {
        JSONArray responseBody = new JSONArray(response.body());

        JSONObject produtoEncontrado = null;

        for (int i = 0; i < responseBody.length(); i++) {
            JSONObject produto = responseBody.getJSONObject(i);
            if (produtoId.equals(produto.getString("id"))) {
                produtoEncontrado = produto;
                break;
            }
        }

        assertNotNull(produtoEncontrado);
        assertEquals(produtoId, produtoEncontrado.getString("id"));
        assertNotNull(produtoEncontrado.getString("nome"));
        assertNotNull(produtoEncontrado.getString("categoria"));
    }
}