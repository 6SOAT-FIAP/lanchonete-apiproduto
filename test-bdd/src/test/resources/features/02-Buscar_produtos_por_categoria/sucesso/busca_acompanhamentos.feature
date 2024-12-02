Feature: Validação consulta por categoria acompanhamento

  Scenario: Consultar produtos da categoria acompanhamento
    Given o seguinte produto da categoria acompanhamento foi cadastrado:
      """
      {
        "nome": "Tiras de frango frito",
        "categoria": "ACOMPANHAMENTO",
        "preco": 10,
        "descricao": "Tiras de frango frito no óleo de algodão ",
        "imagem": "https://static.itdg.com.br/images/640-440/49687a8a7a7110c7f560b9c7e96a9d0e/254679-shutterstock-364110890-1-.jpg"
      }
      """
    When envio uma requisição GET para "/api/v1/produto/ACOMPANHAMENTO" para buscar os acompanhamento cadastrados
    Then o código de resposta deve ser 200 para consulta da categoria acompanhamento
    And o corpo da resposta deve conter um produto da categoria ACOMPANHAMENTO