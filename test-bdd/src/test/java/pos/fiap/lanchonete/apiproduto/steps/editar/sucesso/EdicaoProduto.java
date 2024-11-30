package pos.fiap.lanchonete.apiproduto.steps.editar.sucesso;

import io.cucumber.java.en.*;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.Assert.*;

public class EdicaoProduto {

    private HttpResponse<String> response;

    @Given("o seguinte produto foi cadastrado para ser editado:")
    public void o_seguinte_produto_foi_cadastrado(String body) throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8081/api/v1/produto"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    @When("envio uma requisição PUT para {string} com os dados atualizados:")
    public void envio_uma_requisicao_put_para_com_os_dados_atualizados(String endpoint, String updatedBody) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8081" + endpoint))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(updatedBody))
                .build();

        response = client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    @Then("o código de resposta deve ser {int} para edição do produto")
    public void o_codigo_de_resposta_deve_ser_para_edicao_do_produto(int statusCode) {
        assertNotNull(response);
        assertEquals(statusCode, response.statusCode());
    }

    @And("o corpo da resposta deve conter os dados atualizados do produto:")
    public void o_corpo_da_resposta_deve_conter_os_dados_atualizados_do_produto(String expectedBody) {
        JSONObject actualJson = new JSONObject(response.body());

        assertNotNull(actualJson.getString("id"));
        assertEquals("X-bacon Deluxe", actualJson.getString("nome"));
        assertEquals("LANCHE", actualJson.getString("categoria"));
        assertEquals(12.0, actualJson.getDouble("preco"), 0.0001);
        assertEquals("Pão, carne, queijo, bacon e molho especial", actualJson.getString("descricao"));
        assertEquals("https://static.itdg.com.br/images/640-440/49687a8a7a7110c7f560b9c7e96a9d0e/254679-shutterstock-364110890-1-.jpg", actualJson.getString("imagem"));
    }
}
