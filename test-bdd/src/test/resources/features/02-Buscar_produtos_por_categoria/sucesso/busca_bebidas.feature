Feature: Validação consulta por categoria bebida

  Scenario: Consultar produtos da categoria bebida
    Given o seguinte produto da categoria bebida foi cadastrado:
      """
      {
        "nome": "Refrigerante de caju",
        "categoria": "BEBIDA",
        "preco": 10,
        "descricao": "Refrigerante de caju com gengibre",
        "imagem": "https://static.itdg.com.br/images/640-440/49687a8a7a7110c7f560b9c7e96a9d0e/254679-shutterstock-364110890-1-.jpg"
      }
      """
    When envio uma requisição GET para "/api/v1/produto/BEBIDA" para buscar as bebidas cadastradas
    Then o código de resposta deve ser 200 para consulta da categoria bebida
    And o corpo da resposta deve conter um produto da categoria BEBIDA