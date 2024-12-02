Feature: Validação consulta por categoria inconsistente

  Scenario: Cadastro inconsistente
    Given o seguinte produto da categoria sobremesa foi cadastrado para busca inconsistente:
      """
      {
        "nome": "X-sobremesa",
        "categoria": "SOBREMESA",
        "preco": 10,
        "descricao": "Pão, carne, queijo, bacon e sorvete",
        "imagem": "https://static.itdg.com.br/images/640-440/49687a8a7a7110c7f560b9c7e96a9d0e/254679-shutterstock-364110890-1-.jpg"
      }
      """
    When envio uma requisição GET para "/api/v1/produto/SOBREMEZA" para buscar as sobremesas cadastradas com incosistencia
    Then o código de resposta deve ser 404 para consulta da categoria inconsistente