Feature: Validação consulta por categoria lanches

  Scenario: Consultar produtos da categoria lanche
    Given o seguinte produto da categoria lanche foi cadastrado:
      """
      {
        "nome": "X-salada",
        "categoria": "LANCHE",
        "preco": 10,
        "descricao": "Pão, carne, queijo e alface",
        "imagem": "https://static.itdg.com.br/images/640-440/49687a8a7a7110c7f560b9c7e96a9d0e/254679-shutterstock-364110890-1-.jpg"
      }
      """
    When envio uma requisição GET para "/api/v1/produto/LANCHE" para buscar os lanches cadastrados
    Then o código de resposta deve ser 200 para consulta da categoria lanche
    And o corpo da resposta deve conter um produto da categoria LANCHE